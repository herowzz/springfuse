package com.github.herowzz.springfuse.api.dto.param;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "id列表")
public class IdListParam {

	@ApiModelProperty(value = "Id列表", required = true)
	@Size(min = 1, message = "传入的id数组必须大于1")
	private List<String> idList = new ArrayList<>();

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

}
