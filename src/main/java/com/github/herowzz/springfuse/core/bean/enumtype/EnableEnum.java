package com.github.herowzz.springfuse.core.bean.enumtype;

/**
 * 是否启用<br>
 * 0:停用; 1:启用
 * @author wangzz
 */
@EnumName("EnableType")
public enum EnableEnum implements IBaseEnum {

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

	@Override
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

	@Override
	public String getName() {
		return this.name();
	}

	@Override
	public int getValue() {
		return this.ordinal();
	}
}
