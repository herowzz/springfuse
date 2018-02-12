package com.github.herowzz.springfuse.rest.dto.refrence;

public enum ApiResultCodeEnum {

	SERVER_ERROR(5000, "服务器异常"),

	REPEAT_ADD(5001, "重复添加"),

	OBJECT_NOT_FOUND(5002, "对象不存在"),

	PARAM_ERROR(4000, "参数错误"),

	NO_AUTH_OPERATE(4001, "无操作权限"),

	INVALID_TOKEN(4002, "无效Token"),

	AUTH_FAILED(4003, "权限认证失败"),

	HTTP_METHOD_NOT_SUPPORT(4005, "Http方法不支持"),

	HTTP_MEDIATYPE_NOT_SUPPORT(4006, "Http类型MIME不支持");

	private int code;
	private String msg;

	private ApiResultCodeEnum(int code, String msg) {
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
