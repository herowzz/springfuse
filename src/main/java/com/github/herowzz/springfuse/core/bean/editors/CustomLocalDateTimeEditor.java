package com.github.herowzz.springfuse.core.bean.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class CustomLocalDateTimeEditor extends PropertyEditorSupport {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private final boolean allowEmpty;

	private final int exactDateLength;

	public CustomLocalDateTimeEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public CustomLocalDateTimeEditor(boolean allowEmpty, String format) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
		this.formatter = DateTimeFormatter.ofPattern(format);
	}

	public CustomLocalDateTimeEditor(boolean allowEmpty, int exactDateLength) {
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
			setValue(LocalDateTime.parse(text, formatter));
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		LocalDateTime value = (LocalDateTime) getValue();
		return (value != null ? value.toString() : "");
	}

	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime ld = LocalDateTime.parse("05/24/2016", formatter);
		System.out.println(ld);
	}

}
