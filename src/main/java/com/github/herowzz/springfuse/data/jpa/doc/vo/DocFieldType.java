package com.github.herowzz.springfuse.data.jpa.doc.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public enum DocFieldType {

	Date("date"),

	DateTime("dateTime"),

	BigDecimal("decimal"),

	Int("int"),

	Long("bigint"),

	String("varchar");

	private String title;

	DocFieldType(String title) {
		this.title = title;
	}

	public static String getFieldType(Class<?> classz) {
		if (classz.isAssignableFrom(LocalDateTime.class))
			return DateTime.title;
		if (classz.isAssignableFrom(LocalDate.class))
			return Date.title;
		if (classz.isAssignableFrom(BigDecimal.class) || classz.isAssignableFrom(Double.class) || classz.isAssignableFrom(Float.class))
			return BigDecimal.title;
		if (classz.isAssignableFrom(Integer.class))
			return Int.title;
		if (classz.isAssignableFrom(Long.class))
			return Long.title;
		if (classz.isAssignableFrom(String.class))
			return String.title;
		return classz.getSimpleName();
	}

	public String getTitle() {
		return title;
	}

}
