package com.github.herowzz.springfuse.core.util;

import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;

public abstract class CodeUtils {

	public static String getRandomIntCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		return generator.generate(length);
	}

	public static String getRandomAbcCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		return generator.generate(length);
	}

	public static String getRandomStrCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').build();
		return generator.generate(length);
	}

	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
