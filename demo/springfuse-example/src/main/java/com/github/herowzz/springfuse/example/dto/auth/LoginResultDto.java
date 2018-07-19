package com.github.herowzz.springfuse.example.dto.auth;

import java.util.ArrayList;
import java.util.List;

import com.github.herowzz.springfuse.example.domain.account.User;

public class LoginResultDto {

	public String avatar;

	public String username;

	public String realname;

	public String nickname;

	public String phone;

	public String accessToken;

	public Long expireTime;

	/**
	 * 权限列表
	 */
	public List<PermissionDto> permissionList = new ArrayList<>();

	public void addPermission(PermissionDto permission) {
		this.permissionList.add(permission);
	}

	public static LoginResultDto build(User user, String token, Long expireTime) {
		LoginResultDto dto = new LoginResultDto();
		dto.username = user.getUsername();
		dto.realname = user.getRealname();
		dto.avatar = user.getAvatar();
		dto.phone = user.getPhone();
		dto.accessToken = token;
		dto.expireTime = expireTime;
		return dto;
	}
}
