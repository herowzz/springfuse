package com.github.herowzz.springfuse.core.exception;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class ExceptionsTest {

	@Test
	public void testUnchecked() {
		assertThat(Exceptions.unchecked(new Exception()), isA(RuntimeException.class));
		assertThat(Exceptions.unchecked(new ClassCastException()), isA(RuntimeException.class));
		assertThat(Exceptions.unchecked(new CloneNotSupportedException()), isA(RuntimeException.class));
		assertThat(Exceptions.unchecked(new MyException()), isA(RuntimeException.class));
	}

	@Test
	public void testGetStackTraceAsString() {
		try {
			Test1 test1 = new Test1();
			test1.t1();
		} catch (Exception e) {
			String str = Exceptions.getStackTraceAsString(e);
			assertThat(str, containsString("t2 error"));
			assertThat(str, containsString("t3 error"));
			assertThat(str, containsString("by zero"));
		}
	}

	@Test
	public void testGetErrorMessageWithNestedException() {
		try {
			Test1 test1 = new Test1();
			test1.t1();
		} catch (Exception e) {
			String str = Exceptions.getErrorMessageWithNestedException(e);
			str = str.substring(str.indexOf("nested exception"), str.length());
			assertThat(str, not(containsString("t2 error")));
			assertThat(str, containsString("t3 error"));
			assertThat(str, not(containsString("by zero")));
		}
	}

	@Test
	public void testGetRootCause() {
		try {
			Test1 test1 = new Test1();
			test1.t1();
		} catch (Exception e) {
			assertThat(Exceptions.getRootCause(e) instanceof ArithmeticException, is(true));
		}
	}

	@Test
	public void testIsCausedBy() {
		try {
			Test1 test1 = new Test1();
			test1.t1();
		} catch (Exception e) {
			assertThat(Exceptions.isCausedBy(e, ArithmeticException.class), is(true));
			assertThat(Exceptions.isCausedBy(e, MyException.class), is(true));

			assertThat(Exceptions.isCausedBy(e, NullPointerException.class, ClassCastException.class, ArithmeticException.class), is(true));
			assertThat(Exceptions.isCausedBy(e, NullPointerException.class, ClassCastException.class, IllegalArgumentException.class), is(false));
		}
	}

	class Test1 {
		public void t1() throws MyException {
			Test2 t = new Test2();
			t.t2();
		}
	}

	class Test2 {
		public void t2() throws MyException {
			try {
				Test3 t = new Test3();
				t.t3();
			} catch (MyException e) {
				throw new MyException("t2 error", e);
			}
		}
	}

	class Test3 {
		// 将抛出ArithmeticException异常
		public void t3() throws MyException {
			try {
				@SuppressWarnings("unused")
				int c = 1 / 0;
			} catch (Exception e) {
				throw new MyException("t3 error", e);
			}
		}
	}

	public class MyException extends Exception {

		private static final long serialVersionUID = 1L;

		public MyException() {
		}

		public MyException(String message) {
			super(message);
		}

		public MyException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
