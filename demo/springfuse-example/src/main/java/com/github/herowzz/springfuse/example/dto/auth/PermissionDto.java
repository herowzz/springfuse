package com.github.herowzz.springfuse.example.dto.auth;

import com.github.herowzz.springfuse.example.domain.account.Permission;

public class PermissionDto {

	/**
	 * 父权限Id
	 */
	public String parentId;

	/**
	 * 权限码
	 */
	public String code;

	/**
	 * 名称
	 */
	public String name;

	/**
	 * 序号
	 */
	public Integer num;

	/**
	 * 类型
	 */
	public int type;

	public static PermissionDto copy(Permission op) {
		PermissionDto dto = new PermissionDto();
		dto.parentId = op.getParentId();
		dto.code = op.getCode();
		dto.name = op.getName();
		dto.num = op.getNum();
		dto.type = op.getType().ordinal();
		return dto;
	}

}
