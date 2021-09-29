package com.github.herowzz.springfuse.data.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.github.herowzz.springfuse.core.bean.enumtype.EnableEnum;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;

/**
 * 登录用户基类
 * 
 * @author wangzz
 */
@MappedSuperclass
public class BaseUser extends BaseUidEntity {

	private static final long serialVersionUID = 1289678870273931913L;

	@Column(unique = true, updatable = false, nullable = false, length = 20)
	@Comment("用户名")
	private String username;

	@Comment("密码")
	private String password;

	@Comment("真实姓名")
	private String realname;

	@Comment("是否启用")
	private EnableEnum enableType;

	@Comment("上次登录时间")
	private LocalDateTime lastLoginTime;

	@Comment("上次登录IP")
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
