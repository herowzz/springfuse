package com.github.herowzz.springfuse.rest.dto;

import javax.validation.constraints.NotBlank;

public class IdParam {

	@NotBlank
	public String id;

	@Override
	public String toString() {
		return "IdParam [id=" + id + "]";
	}

}
