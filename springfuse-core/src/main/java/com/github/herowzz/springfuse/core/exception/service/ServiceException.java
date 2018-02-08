package com.github.herowzz.springfuse.core.exception.service;

/**
 * 业务封装RunTime异常类
 * 
 * @author wangzz
 * 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 5439268283599342126L;

	private int code = 0;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, int code) {
		super(message);
		this.code = code;
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getCode() {
		return code;
	}
}
