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
import com.github.herowzz.springfuse.rest.dto.ResultDTO;
import com.github.herowzz.springfuse.rest.dto.refrence.ApiResultCode;

import io.jsonwebtoken.JwtException;

public class GlobalHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultDTO<?>> error(HttpServletRequest request, Exception ex) {
		logger.error("Rest request error! URL: " + request.getRequestURI(), ex);
		ResultDTO<?> dto = new ResultDTO<>();
		if (ex.getCause() instanceof IllegalArgumentException || ex instanceof JsonProcessingException || ex instanceof JsonMappingException) {
			dto = ResultDTO.paramError();
			dto.msg += "(类型异常:" + ex.getLocalizedMessage() + ")";
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		if (ex.getCause() instanceof InvalidFormatException) {
			InvalidFormatException ife = ((InvalidFormatException) ex.getCause());
			dto = ResultDTO.paramError();
			dto.msg += "(类型异常:参数[" + ife.getPathReference() + "] 数据类型是'" + ife.getTargetType() + "',传入参数的值是:'" + ife.getValue() + "')  " + ex.getLocalizedMessage() + ")";
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		dto = ResultDTO.serverError();
		dto.msg += "(" + ex.getLocalizedMessage() + ")";
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResultDTO<?>> HttpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
		ResultDTO<?> dto = ResultDTO.paramError();
		dto.msg += " 请求数据无法转换成正确格式. (" + ex.getLocalizedMessage() + ")";
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResultDTO<?>> MethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException ex) {
		ResultDTO<?> dto = ResultDTO.paramError();
		StringBuilder sb = new StringBuilder();
		sb.append(dto.msg).append("(");
		BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			sb.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		dto.msg = sb.toString();
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResultDTO<?>> MissingServletRequestParameterException(HttpServletRequest request, MissingServletRequestParameterException ex) {
		ResultDTO<?> dto = ResultDTO.paramError();
		dto.msg = ((MissingServletRequestParameterException) ex).getMessage();
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ResultDTO<?>> ServiceException(HttpServletRequest request, ServiceException ex) {
		ResultDTO<?> dto = new ResultDTO<>();
		dto.msg = ((ServiceException) ex).getMessage();
		dto.code = ((ServiceException) ex).getCode();
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(JwtException.class)
	public ResponseEntity<ResultDTO<?>> JwtException(HttpServletRequest request, JwtException ex) {
		ResultDTO<?> dto = ResultDTO.invalidToken();
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ResultDTO<?>> HttpRequestMethodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
		ResultDTO<?> dto = new ResultDTO<>();
		dto.code = ApiResultCode.HttpMethodNotSupport.getCode();
		dto.msg = ApiResultCode.HttpMethodNotSupport.getMsg() + "(" + ex.getMessage() + ")";
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ResultDTO<?>> HttpRequestMethodNotSupportedException2(HttpServletRequest request, HttpMediaTypeNotSupportedException ex) {
		ResultDTO<?> dto = new ResultDTO<>();
		dto.code = ApiResultCode.HttpMediaTypeNotSupport.getCode();
		dto.msg = ApiResultCode.HttpMediaTypeNotSupport.getMsg() + "(" + ex.getMessage() + ")";
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
	}

}