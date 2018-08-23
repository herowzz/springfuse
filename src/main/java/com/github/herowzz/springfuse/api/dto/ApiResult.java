package com.github.herowzz.springfuse.api.dto;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.herowzz.springfuse.api.dto.refrence.ApiResultCodeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Api访问结果基类
 * @author wangzz
 */
@ApiModel(description = "返回结果")
public class ApiResult<T> implements Serializable {

	private static Logger logger = LoggerFactory.getLogger(ApiResult.class);

	private static final long serialVersionUID = -7860411875580619118L;

	/**
	 * 访问结果<br>
	 * 0：失败<br>
	 * 1：成功
	 */
	@ApiModelProperty(value = "请求结果", allowableValues = "range[0,1]")
	public int result = 1;

	/**
	 * 返回码
	 */
	@ApiModelProperty(value = "返回码")
	public int code = 1;

	/**
	 * 返回信息
	 */
	@ApiModelProperty(value = "返回信息")
	public String msg = "";

	/**
	 * 返回的数据
	 */
	@ApiModelProperty(value = "返回数据")
	public T data;

	public ApiResult() {
	}

	public ApiResult(T data) {
		this.data = data;
		this.result = 1;
		this.code = 1;
		this.msg = "ok";
	}

	public static <T> ApiResult<T> build(T data) {
		ApiResult<T> dto = new ApiResult<>();
		dto.data = data;
		dto.result = 1;
		dto.code = 1;
		dto.msg = "ok";
		return dto;
	}

	public static ApiResult<?> ok() {
		ApiResult<?> dto = new ApiResult<>();
		dto.result = 1;
		dto.code = 1;
		dto.msg = "ok";
		return dto;
	}

	public static ApiResult<?> error(int code, String msg) {
		ApiResult<?> dto = new ApiResult<>();
		dto.result = 0;
		dto.code = code;
		dto.msg = msg;
		return dto;
	}

	public ApiResult<T> errorResult(int code, String msg) {
		this.result = 0;
		this.code = code;
		this.msg = msg;
		return this;
	}

	public static ApiResult<?> serverError() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.SERVER_ERROR;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> invalidToken() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.INVALID_TOKEN;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> paramError() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.PARAM_ERROR;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> authFailed() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.AUTH_FAILED;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> repeatAdd() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.REPEAT_ADD;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> notAuthOperate() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.NO_AUTH_OPERATE;
		return error(resCode.code, resCode.msg);
	}

	public static ApiResult<?> objectNotFound() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.OBJECT_NOT_FOUND;
		return error(resCode.code, resCode.msg);
	}

	public ApiResult<T> objectNotFoundResult() {
		ApiResultCodeEnum resCode = ApiResultCodeEnum.OBJECT_NOT_FOUND;
		return this.errorResult(resCode.code, resCode.msg);
	}

	public String toJson() {
		String resultJson = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			resultJson = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error("ApiResult format json error!", e);
		}
		return resultJson;
	}

}
