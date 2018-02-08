package com.github.herowzz.springfuse.core.bean.editors;

import java.beans.PropertyEditorSupport;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.util.StringUtils;

public class CustomLocalTimeEditor extends PropertyEditorSupport {
	
	private final boolean allowEmpty;

	private final int exactDateLength;

	public CustomLocalTimeEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
		this.exactDateLength = -1;
	}

	public CustomLocalTimeEditor(boolean allowEmpty, int exactDateLength) {
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
			setValue(LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME));
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	@Override
	public String getAsText() {
		LocalTime value = (LocalTime) getValue();
		return (value != null ? value.toString() : "");
	}
	
	public static void main(String[] args) {
		LocalTime ld = LocalTime.parse("10:22:40", DateTimeFormatter.ISO_LOCAL_TIME);
		System.out.println(ld);
	}

}
