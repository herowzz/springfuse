package com.github.herowzz.springfuse.api.dto.param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 请求参数基类
 * @author wangzz
 */
public interface IBaseParam {

	static Logger logger = LoggerFactory.getLogger(IBaseParam.class);

	default String toJson() {
		String resultJson = "";
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		try {
			resultJson = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error("IBaseParam format json error!", e);
		}
		return resultJson;
	}

}
