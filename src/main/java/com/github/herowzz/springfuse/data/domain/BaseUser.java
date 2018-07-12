package com.github.herowzz.springfuse.data.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;

/**
 * 登录用户基类
 * @author wangzz
 */
@MappedSuperclass
public class BaseUser extends BaseUidEntity {

	private static final long serialVersionUID = 1289678870273931913L;

	@Column(length = 20)
	private String username;

	@Column(length = 20)
	private String password;

	@Column(length = 50)
	private String realname;

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

	public EnableEnum getEnableType() {
		return enableType;
	}

	public void setEnableType(EnableEnum enableType) {
		this.enableType = enableType;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
