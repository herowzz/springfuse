package com.github.herowzz.springfuse.example.dto;

import com.github.herowzz.springfuse.api.dto.ApiResult;

public enum ResultEnum {

	USER_IS_DISABLED(10011, "用户被禁用"),

	USER_PASSWORD_ERROR(10012, "用户名密码错误");

	private int code;
	private String msg;

	private ResultEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public ApiResult toResult() {
		return ApiResult.error(code, msg);
	}
}
