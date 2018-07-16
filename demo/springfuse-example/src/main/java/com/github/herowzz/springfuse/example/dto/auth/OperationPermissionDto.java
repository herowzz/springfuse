package com.github.herowzz.springfuse.example.dto.auth;

import com.github.herowzz.springfuse.example.domain.account.OperationPermission;

public class OperationPermissionDto {

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

	public static OperationPermissionDto copy(OperationPermission op) {
		OperationPermissionDto dto = new OperationPermissionDto();
		dto.code = op.getCode();
		dto.name = op.getName();
		dto.num = op.getNum();
		return dto;
	}

}
