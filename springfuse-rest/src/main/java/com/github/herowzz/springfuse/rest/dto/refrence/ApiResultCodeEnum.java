package com.github.herowzz.springfuse.rest.dto.refrence;

public enum ApiResultCode {

	ServerError(5000, "服务器异常"),

	RepeatAdd(5001, "重复添加"),

	ObjectNotFound(5002, "对象不存在"),

	ParamError(4000, "参数错误"),

	NotAuthOperate(4001, "无操作权限"),

	InvalidToken(4002, "无效Token"),

	AuthFailed(4003, "权限认证失败"),

	HttpMethodNotSupport(4005, "Http方法不支持"),

	HttpMediaTypeNotSupport(4006, "Http类型MIME不支持");

	private int code;
	private String msg;

	private ApiResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
