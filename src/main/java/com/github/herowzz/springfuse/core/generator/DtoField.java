package com.github.herowzz.springfuse.core.generator;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.text.WordUtils;

public class DtoField {

	private String name;

	private String classType;

	private Object timeType;

	private boolean manyToOne = false;

	private String manyToOneName = "";

	public String getTimeFormat() {
		String str = "";
		if (timeType != null) {
			if (timeType.getClass().isInstance(LocalDate.class))
				str = "yyyy-MM-dd";
			if (timeType.getClass().isInstance(LocalDateTime.class))
				str = "yyyy-MM-dd HH:mm:ss";
		}
		return str;
	}

	public String getNameCapital() {
		return WordUtils.capitalize(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Object getTimeType() {
		return timeType;
	}

	public void setTimeType(Object timeType) {
		this.timeType = timeType;
	}

	public boolean isManyToOne() {
		return manyToOne;
	}

	public void setManyToOne(boolean manyToOne) {
		this.manyToOne = manyToOne;
	}

	public String getManyToOneName() {
		return manyToOneName;
	}

	public void setManyToOneName(String manyToOneName) {
		this.manyToOneName = manyToOneName;
	}

}
