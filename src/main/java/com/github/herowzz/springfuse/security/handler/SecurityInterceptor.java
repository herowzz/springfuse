package com.github.herowzz.springfuse.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.security.manager.ITokenManager;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

	private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Autowired
	private ITokenManager tokenManager;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String token = request.getHeader("token");
		if (!StringUtils.hasText(token)) {
			logger.warn("Remote IP:{}, Request Url:{} Not Authorized, not has token.", request.getRemoteAddr(), request.getRequestURI());
			response.getWriter().write(ApiResult.invalidToken().toJson());
			response.getWriter().flush();
			return false;
		} else {
			String uid = tokenManager.getUidByToken(token);
			if (uid == null) {
				logger.warn("Remote IP:{}, Request Url:{} Not Authorized, token:{} is invalid!", request.getRemoteAddr(), request.getRequestURI(), token);
				response.getWriter().write(ApiResult.invalidToken().toJson());
				response.getWriter().flush();
				return false;
			}
		}
		return true;
	}

}