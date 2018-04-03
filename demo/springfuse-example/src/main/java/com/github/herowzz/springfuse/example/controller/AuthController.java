package com.github.herowzz.springfuse.example.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.example.domain.User;
import com.github.herowzz.springfuse.example.dto.ResultEnum;
import com.github.herowzz.springfuse.example.dto.auth.LoginResultDto;
import com.github.herowzz.springfuse.example.dto.auth.param.LoginParam;
import com.github.herowzz.springfuse.example.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	public ApiResult login(@RequestBody @Valid LoginParam param) {
		User user = userService.findByUsernameAndPassword(param.username, param.password);
		if (user == null)
			return ResultEnum.USER_PASSWORD_ERROR.toResult();
		if (user.getEnableType() == EnableEnum.Disable)
			return ResultEnum.USER_IS_DISABLED.toResult();
		return ApiResult.build(LoginResultDto.build(user));
	}

}
