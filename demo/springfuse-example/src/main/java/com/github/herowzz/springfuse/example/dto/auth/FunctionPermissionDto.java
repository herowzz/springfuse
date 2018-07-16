package com.github.herowzz.springfuse.example.dto.auth;

import java.util.ArrayList;
import java.util.List;

import com.github.herowzz.springfuse.example.domain.account.FunctionPermission;

public class FunctionPermissionDto {

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
	 * 操作列表
	 */
	public List<OperationPermissionDto> operationPermissionList = new ArrayList<>();

	public void addOperationPermission(OperationPermissionDto operationPermission) {
		this.operationPermissionList.add(operationPermission);
	}

	public static FunctionPermissionDto copy(FunctionPermission op) {
		FunctionPermissionDto dto = new FunctionPermissionDto();
		dto.parentId = op.getParentId();
		dto.code = op.getCode();
		dto.name = op.getName();
		dto.num = op.getNum();
		return dto;
	}

}
