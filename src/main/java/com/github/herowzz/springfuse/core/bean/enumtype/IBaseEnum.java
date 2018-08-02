package com.github.herowzz.springfuse.core.bean.enumtype;

import com.fasterxml.jackson.annotation.JsonValue;

public interface IBaseEnum {

	public String getTitle();

	public String getName();

	@JsonValue
	public int getValue();
}
