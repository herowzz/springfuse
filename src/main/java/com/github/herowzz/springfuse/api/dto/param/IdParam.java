package com.github.herowzz.springfuse.api.dto.param;

import javax.validation.constraints.NotBlank;

/**
 * Id请求参数
 * @author wangzz
 *
 */
public class IdParam implements IBaseParam {

	@NotBlank
	public String id;

	@Override
	public String toString() {
		return "IdParam [id=" + id + "]";
	}

}
