package com.github.herowzz.springfuse.example.dto.auth;

import com.github.herowzz.springfuse.example.domain.User;

public class LoginResultDto {

	public String sessionId;

	public String avatar;

	public String username;

	public String realname;

	public String nickname;

	public String phone;

	public String accessToken;

	public Long expireTime;

	public static LoginResultDto build(User user, String token, Long expireTime) {
		LoginResultDto dto = new LoginResultDto();
		dto.sessionId = user.getId();
		dto.username = user.getUsername();
		dto.realname = user.getRealname();
		dto.avatar = user.getAvatar();
		dto.phone = user.getPhone();
		dto.accessToken = token;
		dto.expireTime = expireTime;
		return dto;
	}
}
