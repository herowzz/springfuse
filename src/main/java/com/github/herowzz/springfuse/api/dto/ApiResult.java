package com.github.herowzz.springfuse.api.dto;

import java.io.Serializable;

import com.github.herowzz.springfuse.api.dto.refrence.ApiResultCodeEnum;

/**
 * Api访问结果基类
 * @author wangzz
 */
public class ApiResult implements Serializable {

	private static final long serialVersionUID = -7860411875580619118L;

	/**
	 * 访问结果<br>
	 * 0：失败<br>
	 * 1：成功
	 */
	public int result = 1;

	/**
	 * 返回码
	 */
	public int code = 1;

	/**
	 * 返回信息
	 */
	public String msg = "";

	/**
	 * 返回的数据
	 */
	public Object data;

	public ApiResult() {
	}

	public <T> ApiResult(T data) {
		this.data = data;
		this.result = 1;
		this.code = 1;
		this.msg = "ok";
	}

	public static <T> ApiResult build(T data) {
		ApiResult dto = new ApiResult();
		dto.data = data;
		dto.result = 1;
		dto.code = 1;
		dto.msg = "ok";
		return dto;
	}

	public static ApiResult ok() {
		ApiResult dto = new ApiResult();
		dto.result = 1;
		dto.code = 1;
		dto.msg = "ok";
		return dto;
	}

	public static ApiResult error(int code, String msg) {
		ApiResult dto = new ApiResult();
		dto.result = 0;
		dto.code = code;
		dto.msg = msg;
		return dto;
	}

	public static ApiResult serverError() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.SERVER_ERROR;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult invalidToken() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.INVALID_TOKEN;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult paramError() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.PARAM_ERROR;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult authFailed() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.AUTH_FAILED;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult repeatAdd() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.REPEAT_ADD;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult objectNotFound() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.OBJECT_NOT_FOUND;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ApiResult notAuthOperate() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.NO_AUTH_OPERATE;
		return error(resCode.getCode(), resCode.getMsg());
	}

}
