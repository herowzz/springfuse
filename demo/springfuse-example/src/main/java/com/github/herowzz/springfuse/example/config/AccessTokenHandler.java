package com.github.herowzz.springfuse.example.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import com.github.herowzz.springfuse.api.dto.refrence.ApiResultCodeEnum;
import com.github.herowzz.springfuse.api.handler.GlobalHandler;
import com.github.herowzz.springfuse.core.exception.service.ServiceException;
import com.github.herowzz.springfuse.example.domain.account.User;
import com.github.herowzz.springfuse.example.service.account.UserService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;

@ControllerAdvice
public class AccessTokenHandler extends GlobalHandler {

	@Autowired
	private ITokenManager tokenManager;

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void addAttributes(@RequestHeader(name = "token", required = false) String token, Model model) {
		if (token != null) {
			String uid = tokenManager.getUidByToken(token);
			Optional<User> userOptional = userService.findById(uid);
			if (!userOptional.isPresent()) {
				throw new ServiceException("Permission Error(Incorrect token), token: " + token + ", find user id:" + uid + ", find user is not exist!", ApiResultCodeEnum.OBJECT_NOT_FOUND.code);
			}
			model.addAttribute("user", userOptional.get());
			UserSessionManager.setUser(userOptional.get());
		}
	}

}
