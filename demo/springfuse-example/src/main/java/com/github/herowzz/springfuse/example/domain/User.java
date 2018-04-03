package com.github.herowzz.springfuse.example.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.data.jpa.domain.BaseIncreaseEntity;

@Entity
public class User extends BaseIncreaseEntity {

	private static final long serialVersionUID = 7541116354582183048L;

	@Column(length = 20)
	private String username;

	@Column(length = 20)
	private String password;

	@Column(length = 20)
	private String phone;

	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String realname;

	@Column(length = 50)
	private String nickname;

	@Column(length = 200)
	private String avatar;

	private EnableEnum enableType;

	private LocalDateTime lastLoginTime;

	@Column(length = 20)
	private String lastLoginIp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDateTime getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(LocalDateTime lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public EnableEnum getEnableType() {
		return enableType;
	}

	public void setEnableType(EnableEnum enableType) {
		this.enableType = enableType;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
