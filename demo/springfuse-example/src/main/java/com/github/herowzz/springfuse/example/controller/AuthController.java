package com.github.herowzz.springfuse.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.example.domain.account.FunctionPermission;
import com.github.herowzz.springfuse.example.domain.account.OperationPermission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.dto.ResultEnum;
import com.github.herowzz.springfuse.example.dto.auth.FunctionPermissionDto;
import com.github.herowzz.springfuse.example.dto.auth.LoginResultDto;
import com.github.herowzz.springfuse.example.dto.auth.OperationPermissionDto;
import com.github.herowzz.springfuse.example.dto.auth.param.LoginParam;
import com.github.herowzz.springfuse.example.service.account.AuthService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.model.TokenModel;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private ITokenManager tokenManager;

	@RequestMapping(value = "/login")
	public ApiResult login(@RequestBody @Valid LoginParam param, HttpServletRequest request) {
		User user = authService.findUserByUsernameAndPassword(param.username, param.password);
		if (user == null)
			return ResultEnum.USER_PASSWORD_ERROR.toResult();
		if (user.getEnableType() == EnableEnum.Disable)
			return ResultEnum.USER_IS_DISABLED.toResult();

		TokenModel tokenModel = tokenManager.createToken(user.getId());
		authService.login(user, request.getRemoteAddr());

		LoginResultDto resultDto = LoginResultDto.build(user, tokenModel.getToken(), tokenModel.getExpireTime());
		List<Role> roleList = authService.findRoleListByUser(user.getId());
		List<FunctionPermission> funcPermissionList = authService.findFunctionPermissionByRole(roleList);
		for (FunctionPermission funcPerm : funcPermissionList) {
			FunctionPermissionDto functionPermissionDto = FunctionPermissionDto.copy(funcPerm);
			List<OperationPermission> operPermList = authService.findByFunctionPermission(funcPerm.getId());
			operPermList.stream().forEach(o -> functionPermissionDto.addOperationPermission(OperationPermissionDto.copy(o)));
			resultDto.addFunctionPermission(functionPermissionDto);
		}
		return ApiResult.build(resultDto);
	}

	@RequestMapping(value = "/exit")
	public ApiResult exit() {
		return ApiResult.ok();
	}

}
