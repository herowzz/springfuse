package com.github.herowzz.springfuse.example.domain.refrence;

import com.github.herowzz.springfuse.core.bean.enumtype.IBaseEnum;

/**
 * 权限类型
 * @author wangzz
 */
public enum PermissionTypeEnum implements IBaseEnum {

	/**
	 * 模块
	 */
	MODULE("模块"),

	/**
	 * 按钮
	 */
	BUTTON("按钮");

	private String title;

	private PermissionTypeEnum(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public static PermissionTypeEnum get(int index) {
		return PermissionTypeEnum.values()[index];
	}

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public int getValue() {
		return this.ordinal();
	}

}
