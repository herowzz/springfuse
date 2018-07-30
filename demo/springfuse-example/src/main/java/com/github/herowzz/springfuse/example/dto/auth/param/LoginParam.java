package com.github.herowzz.springfuse.example.dto.auth.param;

import javax.validation.constraints.NotBlank;

import com.github.herowzz.springfuse.api.dto.param.IBaseParam;

public class LoginParam implements IBaseParam {

	@NotBlank
	public String username;

	@NotBlank
	public String password;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginParam [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

}
