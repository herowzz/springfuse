package com.github.herowzz.springfuse.rest.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.herowzz.springfuse.core.exception.service.ServiceException;
import com.github.herowzz.springfuse.rest.dto.ResultDTO;

import io.jsonwebtoken.JwtException;

public class GlobalHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResultDTO<?>> error(HttpServletRequest request, Exception ex) {
		logger.error("Rest request error! URL: " + request.getRequestURI(), ex);
		ResultDTO<?> dto = new ResultDTO<>();
		if (ex instanceof MethodArgumentNotValidException) {
			dto = ResultDTO.paramError();
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
		if (ex instanceof MissingServletRequestParameterException) {
			dto = ResultDTO.paramError();
			dto.msg = ((MissingServletRequestParameterException) ex).getMessage();
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		if (ex instanceof ServiceException) {
			dto = new ResultDTO<>();
			dto.msg = ((ServiceException) ex).getMessage();
			dto.code = ((ServiceException) ex).getCode();
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		if (ex instanceof JwtException) {
			dto = ResultDTO.invalidToken();
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		if (ex.getCause() instanceof IllegalArgumentException || ex instanceof JsonProcessingException || ex instanceof JsonMappingException) {
			dto = ResultDTO.paramError();
			dto.msg += "(类型异常:" + ex.getLocalizedMessage() + ")";
			return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.OK);
		}
		dto = ResultDTO.serverError();
		dto.msg += "(" + ex.getLocalizedMessage() + ")";
		return new ResponseEntity<ResultDTO<?>>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}