package com.github.herowzz.springfuse.rest.dto;

import java.io.Serializable;

import com.github.herowzz.springfuse.rest.dto.refrence.ApiResultCode;

/**
 * 访问结果基类
 * @author wangzz
 */
public class ResultDTO<T> implements Serializable {

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
	public T data;

	public ResultDTO() {
	}

	public ResultDTO(T data) {
		this.data = data;
		this.result = 1;
		this.code = 1;
		this.msg = "ok";
	}

	public static ResultDTO<?> ok() {
		ResultDTO<?> dto = new ResultDTO<>();
		dto.result = 1;
		dto.code = 1;
		dto.msg = "ok";
		return dto;
	}

	public static ResultDTO<?> error(int code, String msg) {
		ResultDTO<?> dto = new ResultDTO<>();
		dto.result = 0;
		dto.code = code;
		dto.msg = msg;
		return dto;
	}

	public static ResultDTO<?> serverError() {
		ApiResultCode resCode = ApiResultCode.ServerError;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> invalidToken() {
		ApiResultCode resCode = ApiResultCode.InvalidToken;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> paramError() {
		ApiResultCode resCode = ApiResultCode.ParamError;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> authFailed() {
		ApiResultCode resCode = ApiResultCode.AuthFailed;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> repeatAdd() {
		ApiResultCode resCode = ApiResultCode.RepeatAdd;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> objectNotFound() {
		ApiResultCode resCode = ApiResultCode.ObjectNotFound;
		return error(resCode.getCode(), resCode.getMsg());
	}

	public static ResultDTO<?> notAuthOperate() {
		ApiResultCode resCode = ApiResultCode.NotAuthOperate;
		return error(resCode.getCode(), resCode.getMsg());
	}

}
