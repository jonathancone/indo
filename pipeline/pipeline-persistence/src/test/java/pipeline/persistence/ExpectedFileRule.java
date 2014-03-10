package pipeline.persistence;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.joor.Reflect;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ExpectedFileRule implements TestRule {

	private static final String INPUT_TEMPLATE = "%s-input.txt";
	private static final String EXPECTED_TEMPLATE = "%s-expected.txt";

	private Object testObject;

	public ExpectedFileRule(Object testObject) {
		this.testObject = testObject;
	}

	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {

				String expectedFile = getExpectedFile(description);

				if (expectedFile != null) {
					Class<?> testClass = description.getTestClass();

					URL url = testClass.getResource(expectedFile);

					File file = FileUtils.toFile(url);

				}

				try {
					base.evaluate();

				} finally {

				}
			}
		};
	}

	/**
	 * Based on the {@link Description}, determine the name of the
	 * {@link ExpectedFile}. This implementation allows for two schemes:
	 * <ol>
	 * <li>The class can be annotated</li>
	 * <li></li>
	 * </ol>
	 * 
	 * @param description
	 * @return
	 */
	protected String getExpectedFile(Description description) {

		Class<?> testClass = description.getTestClass();
		Method testMethod = testClass.getMethod(description.getMethodName());

		AnnotationDetails<ExpectedFile> expectedFileDetails = AnnotationFinder
				.findAnnotation(testClass, testMethod,
						ExpectedFile.class);

		String inputFile = null;
		String expectedFile = null;

		/*
		 * First, check to see if the method has the annotation.
		 */
		if (methodAnnotation != null) {
			expectedFile = StringUtils.isNotBlank(methodAnnotation
					.expectedFile()) ? methodAnnotation
					.expectedFile()
					: buildFileName(EXPECTED_TEMPLATE, description);

			inputFile = StringUtils.isNotBlank(methodAnnotation
					.inputFile()) ? methodAnnotation
					.inputFile()
					: buildFileName(INPUT_TEMPLATE, description);
		} else {
			/**
			 * Maybe the class is annotated?
			 */
			Class<?> testClass = description.getTestClass();

			ExpectedFile classAnnotation = testClass
					.getAnnotation(ExpectedFile.class);

			if (classAnnotation != null) {
				expectedFile = StringUtils.isNotBlank(classAnnotation
						.expectedFile()) ? classAnnotation
						.expectedFile()
						: buildFileName(EXPECTED_TEMPLATE, description);

				inputFile = StringUtils.isNotBlank(classAnnotation
						.inputFile()) ? classAnnotation
						.inputFile()
						: buildFileName(INPUT_TEMPLATE, description);
			}

		}

		return expectedFile;
	}

	private String buildFileName(String template, Description description) {

		String className = description.getTestClass().getSimpleName();

		String filePattern = className + "." + description.getMethodName();

		return String.format(template, filePattern);
	}

	public Object getTestObject() {
		return testObject;
	}

}
