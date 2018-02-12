package com.github.herowzz.springfuse.rest.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.herowzz.springfuse.core.exception.service.ServiceException;
import com.github.herowzz.springfuse.rest.dto.ApiResult;
import com.github.herowzz.springfuse.rest.dto.refrence.ApiResultCodeEnum;

import io.jsonwebtoken.JwtException;

public class GlobalHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResult<?>> error(HttpServletRequest request, Exception ex) {
		logger.error("Rest request error! URL: " + request.getRequestURI(), ex);
		ApiResult<?> dto = new ApiResult<>();
		if (ex.getCause() instanceof IllegalArgumentException || ex instanceof JsonProcessingException || ex instanceof JsonMappingException) {
			dto = ApiResult.paramError();
			dto.msg += "(类型异常:" + ex.getLocalizedMessage() + ")";
			return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
		}
		if (ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException ife = ((InvalidFormatException) ex.getCause());
			dto = ApiResult.paramError();
			dto.msg += "(类型异常:参数[" + ife.getPathReference() + "] 数据类型是'" + ife.getTargetType() + "',传入参数的值是:'" + ife.getValue() + "')  " + ex.getLocalizedMessage() + ")";
			return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
		}
		dto = ApiResult.serverError();
		dto.msg += "(" + ex.getLocalizedMessage() + ")";
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApiResult<?>> HttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
		ApiResult<?> dto = ApiResult.paramError();
		dto.msg += " 请求数据无法转换成正确格式. (" + ex.getLocalizedMessage() + ")";
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResult<?>> MethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
		ApiResult<?> dto = ApiResult.paramError();
		StringBuilder sb = new StringBuilder();
		sb.append(dto.msg).append("(");
		BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		dto.msg = sb.toString();
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ApiResult<?>> MissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
		ApiResult<?> dto = ApiResult.paramError();
		dto.msg = ((MissingServletRequestParameterException) ex).getMessage();
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ApiResult<?>> ServiceException(HttpServletRequest request, ServiceException ex) {
		ApiResult<?> dto = new ApiResult<>();
		dto.msg = ((ServiceException) ex).getMessage();
		dto.code = ((ServiceException) ex).getCode();
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ApiResult<?>> JwtException(HttpServletRequest request, JwtException ex) {
		ApiResult<?> dto = ApiResult.invalidToken();
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ApiResult<?>> HttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
		ApiResult<?> dto = new ApiResult<>();
		dto.code = ApiResultCodeEnum.HTTP_METHOD_NOT_SUPPORT.getCode();
		dto.msg = ApiResultCodeEnum.HTTP_METHOD_NOT_SUPPORT.getMsg() + "(" + ex.getMessage() + ")";
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ApiResult<?>> HttpRequestMethodNotSupportedException2(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
		ApiResult<?> dto = new ApiResult<>();
		dto.code = ApiResultCodeEnum.HTTP_MEDIATYPE_NOT_SUPPORT.getCode();
		dto.msg = ApiResultCodeEnum.HTTP_MEDIATYPE_NOT_SUPPORT.getMsg() + "(" + ex.getMessage() + ")";
		return new ResponseEntity<ApiResult<?>>(dto, HttpStatus.OK);
	}

}