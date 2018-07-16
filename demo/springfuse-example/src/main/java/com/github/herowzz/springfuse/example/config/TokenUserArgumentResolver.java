package com.github.herowzz.springfuse.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.github.herowzz.springfuse.api.dto.refrence.ApiResultCodeEnum;
import com.github.herowzz.springfuse.core.exception.service.ServiceException;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.service.account.UserService;
import com.github.herowzz.springfuse.security.annotation.CurrentUser;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;

@Component
public class TokenUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private ITokenManager tokenManager;

	@Autowired
	private UserService userService;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
		if (parameter.getParameterType().isAssignableFrom(User.class) && parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String token = webRequest.getHeader("token");
		if (token != null) {
			String uid = tokenManager.getUidByToken(token);
			Optional<User> userOptional = userService.findById(uid);
			if (!userOptional.isPresent()) {
				throw new ServiceException("Permission Error(Incorrect token), token: " + token + "find user id:" + uid + ", find user is not exist!", ApiResultCodeEnum.OBJECT_NOT_FOUND.code);
			}
			UserSessionManager.setUser(userOptional.get());
			return userOptional.get();
		}
		throw new ServiceException("Permission Error(not has token.)", ApiResultCodeEnum.INVALID_TOKEN.code);
	}
}