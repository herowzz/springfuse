package com.github.herowzz.springfuse.core.bean.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.github.herowzz.springfuse.api.dto.param.PageParam;
import com.github.herowzz.springfuse.config.ConfigParam;

public class PageCommon {

	public static PageRequest getPage(PageParam pageParam) {
		if (pageParam == null) {
			pageParam = new PageParam(ConfigParam.pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize);
	}

	public static PageRequest getPage(PageParam pageParam, int pageSize) {
		if (pageParam == null) {
			pageParam = new PageParam(pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize);
	}

	public static PageRequest getPage(PageParam pageParam, Sort sort) {
		if (pageParam == null) {
			pageParam = new PageParam(ConfigParam.pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize, sort);
	}

	public static PageRequest getPage(PageParam pageParam, int pageSize, Sort sort) {
		if (pageParam == null) {
			pageParam = new PageParam(pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize, sort);
	}

	public static PageRequest getPage(PageParam pageParam, Direction direction, String... properties) {
		if (pageParam == null) {
			pageParam = new PageParam(ConfigParam.pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize, direction, properties);
	}

	public static PageRequest getPage(PageParam pageParam, int pageSize, Direction direction, String... properties) {
		if (pageParam == null) {
			pageParam = new PageParam(pageSize);
		}
		return PageRequest.of(pageParam.pageNo - 1, pageParam.pageSize, direction, properties);
	}
}
