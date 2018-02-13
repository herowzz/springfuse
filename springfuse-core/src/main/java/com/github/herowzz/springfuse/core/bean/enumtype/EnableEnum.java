package com.github.herowzz.springfuse.core.bean.enumtype;

/**
 * 是否启用<br>
 * 0:停用; 1:启用
 * @author wangzz
 */
public enum EnableEnum {

	/**
	 * 停用
	 */
	Disable("停用"),

	/**
	 * 启用
	 */
	Enable("启用");

	private String title;

	private EnableEnum(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	@Override
	public String toString() {
		return this.getTitle();
	}

	public static EnableEnum get(int index) {
		return EnableEnum.values()[index];
	}

	public String getName() {
		return this.name();
	}

	public int getValue() {
		return this.ordinal();
	}
}
