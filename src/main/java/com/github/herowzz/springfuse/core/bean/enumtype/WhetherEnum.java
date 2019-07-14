package com.github.herowzz.springfuse.core.bean.enumtype;

/**
 * 是否类型<br>
 * 0:否; 1:是
 * @author wangzz
 */
@EnumName("WhetherType")
public enum WhetherEnum implements IBaseEnum {

	No("否"),

	Yes("是");

	private String title;

	private WhetherEnum(String title) {
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

	public static WhetherEnum get(int index) {
		return WhetherEnum.values()[index];
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
