package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求参数基类
 * @author wangzz
 */
public class BasePageParam {

	@ApiModelProperty(value = "分页参数", required = true)
	@NotNull
	@Valid
	public PageParam pageParam;

	/**
	 * 获取分页参数
	 * @return Pageable对象
	 */
	public Pageable getPage() {
		return pageParam.getPage();
	}

	public PageParam getPageParam() {
		return pageParam;
	}

	public void setPageParam(PageParam pageParam) {
		this.pageParam = pageParam;
	}
}
