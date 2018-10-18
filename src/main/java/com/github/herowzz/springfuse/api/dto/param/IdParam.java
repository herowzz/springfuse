package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

/**
 * Id请求参数
 * @author wangzz
 */
@ApiModel(description = "Id请求参数")
public class IdParam implements IBaseParam {

	@NotBlank
	public String id;

	@Override
	public String toString() {
		return "IdParam [id=" + id + "]";
	}

}
