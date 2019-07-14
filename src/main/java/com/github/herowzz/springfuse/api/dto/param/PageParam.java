package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页请求参数
 * @author wangzz
 */
@ApiModel(description = "分页请求参数")
public class PageParam implements IBaseParam {

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "页码(从1开始)", required = true, allowableValues = "range[1, infinity]")
	public Integer num;

	@ApiModelProperty(value = "每页多少条记录")
	public Integer size;

	public Pageable getPage() {
		if (num == null || num < 1)
			num = 1;
		if (size == null)
			size = 10;
		if (size > 1000)
			size = 1000;
		return PageRequest.of(num - 1, size);
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
