package com.github.herowzz.springfuse.example.dto.auth;

import com.github.herowzz.springfuse.example.domain.User;
import com.github.herowzz.springfuse.example.util.JwtUtil;
import com.github.herowzz.springfuse.example.util.SecurityConstant;

public class LoginResultDto {

	public String sessionId;

	public String avatar;

	public String username;

	public String realname;

	public String nickname;

	public String accessToken;

	public String refreshToken;

	public Long expireTime;

	public static LoginResultDto build(User user) {
		LoginResultDto dto = new LoginResultDto();
		dto.sessionId = user.getId() + "";
		dto.username = user.getUsername();
		dto.realname = user.getRealname();
		dto.avatar = user.getAvatar();
		dto.accessToken = JwtUtil.createJWT(user.getId() + "", SecurityConstant.ACCESS_TOKEN_EXPIRE * 60 * 1000);
		dto.refreshToken = JwtUtil.createJWT(user.getId() + "", SecurityConstant.REFRESH_TOKEN_EXPIRE * 60 * 1000);
		dto.expireTime = SecurityConstant.ACCESS_TOKEN_EXPIRE * 24 * 60 * 60 * 1000;
		return dto;
	}
}
