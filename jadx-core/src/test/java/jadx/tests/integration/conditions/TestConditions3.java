package jadx.tests.integration.conditions;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import jadx.tests.api.IntegrationTest;

import static jadx.tests.api.utils.assertj.JadxAssertions.assertThat;

public class TestConditions3 extends IntegrationTest {

	public static class TestCls {
		private static final Pattern PATTERN = Pattern.compile("[a-f0-9]{20}");

		public static Object test(final A a) {
			List<String> list = a.getList();
			if (list == null) {
				return null;
			}
			if (list.size() != 1) {
				return null;
			}
			String s = list.get(0);
			if (isEmpty(s)) {
				return null;
			}
			if (isDigitsOnly(s)) {
				return new A().set(s);
			}
			if (PATTERN.matcher(s).matches()) {
				return new A().set(s);
			}
			return null;
		}

		private static boolean isDigitsOnly(String s) {
			return false;
		}

		private static boolean isEmpty(String s) {
			return false;
		}

		private static class A {
			public Object set(String s) {
				return null;
			}

			public List<String> getList() {
				return null;
			}
		}
	}

	@Test
	public void test() {
		assertThat(getClassNode(TestCls.class))
				.code()
				.contains("return null;")
				.doesNotContain("else")
				.doesNotContain("AnonymousClass_1");
	}
}
