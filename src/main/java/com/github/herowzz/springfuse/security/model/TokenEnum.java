package com.github.herowzz.springfuse.security.model;

public enum TokenEnum {

	Memory,

	Redis;

	public static TokenEnum getByValue(String value) {
		if (value != null) {
			for (TokenEnum tokenEnum : TokenEnum.values()) {
				if (tokenEnum.toString().toLowerCase().equals(value.toLowerCase())) {
					return tokenEnum;
				}
			}
		}
		return TokenEnum.Memory;
	}

}
