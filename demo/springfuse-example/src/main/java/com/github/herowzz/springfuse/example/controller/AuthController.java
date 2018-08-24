package com.github.herowzz.springfuse.example.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.log.annotation.Log;
import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.example.domain.account.Permission;
import com.github.herowzz.springfuse.example.domain.account.Role;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.dto.ResultEnum;
import com.github.herowzz.springfuse.example.dto.auth.LoginResultDto;
import com.github.herowzz.springfuse.example.dto.auth.PermissionDto;
import com.github.herowzz.springfuse.example.dto.auth.param.LoginParam;
import com.github.herowzz.springfuse.example.service.account.AuthService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;
import com.github.herowzz.springfuse.security.model.TokenModel;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
@Api(tags = "权限管理")
public class AuthController {

	@Autowired
	private AuthService authService;

	@Autowired
	private ITokenManager tokenManager;

	@RequestMapping(value = "/login")
	@Log(name = "用户登录", value = "登录系统")
	public ApiResult<LoginResultDto> login(@RequestBody @Valid LoginParam param, HttpServletRequest request) {
		ApiResult<LoginResultDto> result = new ApiResult<>();
		User user = authService.findUserByUsernameAndPassword(param.username, param.password);
		if (user == null)
			return ResultEnum.USER_PASSWORD_ERROR.toResult(result);
		if (user.getEnableType() == EnableEnum.Disable)
			return ResultEnum.USER_IS_DISABLED.toResult(result);

		TokenModel tokenModel = tokenManager.createToken(user.getId());
		authService.login(user, request.getRemoteAddr());
		result.data = LoginResultDto.build(user, tokenModel.getToken(), tokenModel.getExpireTime());
		List<Role> roleList = authService.findRoleListByUser(user.getId());
		Set<Permission> permissions = authService.findPermissions(roleList);
		for (Permission permission : permissions) {
			result.data.addPermission(PermissionDto.copy(permission));
		}
		UserSessionManager.setUser(user);
		return result;
	}

	@RequestMapping(value = "/exit")
	public ApiResult<?> exit() {
		return ApiResult.ok();
	}

}
