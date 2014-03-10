package pipeline.persistence;

import static org.junit.Assert.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.Test;
import org.unitils.util.ReflectionUtils;

/**
 * Unit test cases for {@link AnnotationFinder}.
 * 
 * @author Jonathan Cone
 */
public class AnnotationFinderTest {

	@Test
	public void testUnannotatedClassPublicMethod() throws Exception {
		assertNull(findPath(UnannotatedClass.class, "publicMethod"));
	}

	@Test
	public void testUnannotatedClassPackageMethod() throws Exception {
		assertNull(findPath(UnannotatedClass.class, "method"));
	}

	@Test
	public void testUnannotatedClassProtectedMethod() throws Exception {
		assertNull(findPath(UnannotatedClass.class, "protectedMethod"));
	}

	@Test
	public void testUnannotatedClassPrivateMethod() throws Exception {
		assertNull(findPath(UnannotatedClass.class, "privateMethod"));
	}

	@Test
	public void testAnnotatedClassPublicMethod() throws Exception {
		assertEquals(AnnotatedClass.class.getName(),
				findPath(AnnotatedClass.class, "publicMethod"));
	}

	@Test
	public void testAnnotatedClassPackageMethod() throws Exception {
		assertEquals(AnnotatedClass.class.getName(),
				findPath(AnnotatedClass.class, "method"));
	}

	@Test
	public void testAnnotatedClassProtectedMethod() throws Exception {
		assertEquals(AnnotatedClass.class.getName(),
				findPath(AnnotatedClass.class, "protectedMethod"));
	}

	@Test
	public void testAnnotatedClassPrivateMethod() throws Exception {
		assertEquals(AnnotatedClass.class.getName(),
				findPath(AnnotatedClass.class, "privateMethod"));
	}

	@Test
	public void testAnnotatedMethodPublicMethod() throws Exception {
		assertEquals(AnnotatedMethodClass.class.getName() + ".publicMethod",
				findPath(AnnotatedMethodClass.class, "publicMethod"));
	}

	@Test
	public void testAnnotatedMethodPackageMethod() throws Exception {
		assertEquals(AnnotatedMethodClass.class.getName() + ".method",
				findPath(AnnotatedMethodClass.class, "method"));
	}

	@Test
	public void testAnnotatedMethodProtectedMethod() throws Exception {
		assertEquals(AnnotatedMethodClass.class.getName() + ".protectedMethod",
				findPath(AnnotatedMethodClass.class, "protectedMethod"));
	}

	@Test
	public void testAnnotatedMethodPrivateMethod() throws Exception {
		assertEquals(AnnotatedMethodClass.class.getName() + ".privateMethod",
				findPath(AnnotatedMethodClass.class, "privateMethod"));
	}

	private String findPath(Class<?> target, String methodName) {
		AnnotationDetails<TestAnnotation> details = AnnotationFinder
				.findAnnotation(target,
						ReflectionUtils.getMethod(target,
								methodName, false), TestAnnotation.class);

		String path = details.getPath();
		if (path != null) {
			assertNotNull(
					"The annotation details did not contain the annotation.",
					details.getAnnotation());
		}
		return path;
	}

	@Retention(RetentionPolicy.RUNTIME)
	@Target({ ElementType.METHOD, ElementType.TYPE })
	public @interface TestAnnotation {
	}

	public static class UnannotatedClass {
		public void publicMethod() {
		}

		void method() {
		}

		protected void protectedMethod() {
			privateMethod();
		}

		private void privateMethod() {
		}
	}

	@TestAnnotation
	public static class AnnotatedClass extends UnannotatedClass {
	}

	public static class AnnotatedMethodClass extends UnannotatedClass {
		@TestAnnotation
		@Override
		public void publicMethod() {
		}

		@TestAnnotation
		@Override
		void method() {
		}

		@TestAnnotation
		@Override
		protected void protectedMethod() {
			privateMethod();
		}

		@TestAnnotation
		private void privateMethod() {

		}
	}
}
