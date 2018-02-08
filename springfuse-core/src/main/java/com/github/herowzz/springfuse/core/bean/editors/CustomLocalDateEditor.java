package com.github.herowzz.springfuse.core.bean.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class CustomLocalDateEditor extends PropertyEditorSupport {
	
	private final boolean allowEmpty;

	private final int exactDateLength;

	public CustomLocalDateEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public CustomLocalDateEditor(boolean allowEmpty, int exactDateLength) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = exactDateLength;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		} else if (text != null && this.exactDateLength >= 0 && text.length() != this.exactDateLength) {
			throw new IllegalArgumentException("Could not parse date: it is not exactly" + this.exactDateLength + "characters long");
		} else {
			setValue(LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE));
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		LocalDate value = (LocalDate) getValue();
		return (value != null ? value.toString() : "");
	}
	
	public static void main(String[] args) {
		LocalDate ld = LocalDate.parse("2016-02-01", DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println(ld);
	}

}
