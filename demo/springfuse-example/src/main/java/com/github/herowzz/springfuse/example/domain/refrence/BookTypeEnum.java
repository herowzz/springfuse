package com.github.herowzz.springfuse.example.domain.refrence;

import com.github.herowzz.springfuse.core.bean.enumtype.IBaseEnum;

public enum BookTypeEnum implements IBaseEnum {

	LITERATURE("文学"),

	POLITICAL("政治"),

	ECONOMIC("经济");

	private String title;

	private BookTypeEnum(String title) {
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

	public static BookTypeEnum get(int index) {
		return BookTypeEnum.values()[index];
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
