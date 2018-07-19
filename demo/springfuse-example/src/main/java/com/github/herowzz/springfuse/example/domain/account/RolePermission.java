package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUidEntity;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;

@Entity
@DynamicInsert
@DynamicUpdate
@Comment("角色权限关联")
public class RolePermission extends BaseUidEntity {

	private static final long serialVersionUID = -4375741182420494096L;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Role role;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Permission permission;

	public RolePermission() {
	}

	public RolePermission(Role role, Permission permission) {
		this.role = role;
		this.permission = permission;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

}
