package pipeline.persistence;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joor.Reflect;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ExpectedFileRule implements TestRule {

	private static final String INPUT_TEMPLATE = "%s-input.txt";
	private static final String EXPECTED_TEMPLATE = "%s-expected.txt";

	private String inputFile;
	private String expectedFile;

	private List<String> inputLines;
	private List<String> expectedLines;
	private List<String> actualLines;

	private Object testObject;

	public ExpectedFileRule(Object testObject) {
		this.testObject = testObject;
	}

	void buildActualLines() {
		Class<? extends Object> testClass = testObject.getClass();

		Field[] fields = testClass.getFields();

		for (Field field : fields) {
			ExpectedFile ef = field.getAnnotation(ExpectedFile.class);

			if (ef != null) {
				Reflect.accessible(field);
				Reflect.on(testObject)
						.set(field.getName(), actualLines);
			}
		}

	}

	public Statement apply(final Statement base, final Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {

				String expectedFile = getExpectedFile(description);

				if (expectedFile != null) {
					Class<?> testClass = description.getTestClass();

					InputStream eis = testClass
							.getResourceAsStream(expectedFile);

					expectedLines = IOUtils.readLines(eis);

				}

				try {
					base.evaluate();

				} finally {

				}
			}
		};
	}

	protected String getExpectedFile(Description description) {

		ExpectedFile methodAnnotation = description
				.getAnnotation(ExpectedFile.class);

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
		return String.format(template, description.getClassName() + "."
				+ description.getMethodName());
	}

	public Object getTestObject() {
		return testObject;
	}

	public List<String> getInputLines() {
		return inputLines;
	}

	public List<String> getExpectedLines() {
		return expectedLines;
	}

	public List<String> getActualLines() {
		return actualLines;
	}

}
