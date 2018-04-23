package com.github.herowzz.springfuse.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

import com.github.herowzz.springfuse.api.handler.GlobalHandler;
import com.github.herowzz.springfuse.core.exception.service.ServiceException;
import com.github.herowzz.springfuse.example.service.UserService;
import com.github.herowzz.springfuse.security.manager.ITokenManager;

@ControllerAdvice
public class ResourceHandler extends GlobalHandler {

	@Autowired
	private UserService userService;

	@Autowired
	private ITokenManager tokenManager;

	@ModelAttribute
	public void addAttributes(Model model, @RequestHeader(name = "auth", required = false) String token) {
		if (StringUtils.hasText(token)) {
			String uid = tokenManager.getUidByToken(token);
			if (uid == null) {
				throw new ServiceException("Permission Error(Incorrect auth)");
			}
			
		}
	}

}
