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

/**
 * 角色权限
 * @author wangzz
 */
@Entity(name = "role_function_permission")
@DynamicInsert
@DynamicUpdate
public class RoleFunctionPermission extends BaseUidEntity {

	private static final long serialVersionUID = 6226423822362602730L;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Role role;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private FunctionPermission functionPermission;

	public RoleFunctionPermission() {
	}

	public RoleFunctionPermission(Role role, FunctionPermission functionPermission) {
		this.role = role;
		this.functionPermission = functionPermission;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public FunctionPermission getFunctionPermission() {
		return functionPermission;
	}

	public void setFunctionPermission(FunctionPermission functionPermission) {
		this.functionPermission = functionPermission;
	}

}
