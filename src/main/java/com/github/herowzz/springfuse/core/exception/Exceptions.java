package com.github.herowzz.springfuse.core.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 关于异常的工具类.
 * 
 * 参考了guava的Throwables。
 * 
 * @author wangzz
 * 
 */
public class Exceptions {

	/**
	 * 将CheckedException转换为UncheckedException.
	 * @param ex 需要转换的异常
	 * @return 转换后的UncheckedException异常
	 */
	public static RuntimeException unchecked(Throwable ex) {
		if (ex instanceof RuntimeException) {
			return (RuntimeException) ex;
		} else {
			return new RuntimeException(ex);
		}
	}

	/**
	 * 将ErrorStack转化为String.
	 * @param ex 需要转换的异常
	 * @return 转换后的String
	 */
	public static String getStackTraceAsString(Throwable ex) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	/**
	 * 获取组合本异常信息与底层异常信息的异常描述, 适用于本异常为统一包装异常类，底层异常才是根本原因的情况。
	 * @param ex 异常
	 * @return 底层异常描述
	 */
	public static String getErrorMessageWithNestedException(Throwable ex) {
		StringBuilder sb = new StringBuilder().append(ex.getMessage());
		Throwable nestedException = ex.getCause();
		if (nestedException == null) {
			return sb.toString();
		}
		return sb.append(" nested exception is ").append(nestedException.getClass().getName()).append(":").append(nestedException.getMessage()).toString();
	}

	/**
	 * 获取异常的Root Cause.
	 * @param ex 异常
	 * @return Root Cause
	 */
	public static Throwable getRootCause(Throwable ex) {
		Throwable cause;
		while ((cause = ex.getCause()) != null) {
			ex = cause;
		}
		return ex;
	}

	/**
	 * 判断异常是否由某些底层的异常引起.
	 * @param ex 需要判断的异常
	 * @param causeExceptionClasses 需要匹配的底层异常
	 * @return 是底层异常引起则返回true
	 */
	@SafeVarargs
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex;
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}
}
