package com.github.herowzz.springfuse.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.dto.ResultEnum;
import com.github.herowzz.springfuse.example.dto.auth.LoginResultDto;
import com.github.herowzz.springfuse.example.dto.auth.param.LoginParam;
import com.github.herowzz.springfuse.example.service.account.UserService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.model.TokenModel;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private ITokenManager tokenManager;

	@RequestMapping(value = "/login")
	public ApiResult login(@RequestBody @Valid LoginParam param, HttpServletRequest request) {
		User user = userService.findByUsernameAndPassword(param.username, param.password);
		if (user == null)
			return ResultEnum.USER_PASSWORD_ERROR.toResult();
		if (user.getEnableType() == EnableEnum.Disable)
			return ResultEnum.USER_IS_DISABLED.toResult();

		TokenModel tokenModel = tokenManager.createToken(user.getId());
		userService.login(user, request.getRemoteAddr());
		return ApiResult.build(LoginResultDto.build(user, tokenModel.getToken(), tokenModel.getExpireTime()));
	}

	@RequestMapping(value = "/exit")
	public ApiResult exit() {
		return ApiResult.ok();
	}

}
