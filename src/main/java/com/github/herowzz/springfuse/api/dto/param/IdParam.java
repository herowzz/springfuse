package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Id请求参数
 * @author wangzz
 */
@ApiModel(description = "Id请求参数")
public class IdParam implements IBaseParam {

	@NotBlank
	@ApiModelProperty(required = true)
	public String id;

	@Override
	public String toString() {
		return "IdParam [id=" + id + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
