package com.github.herowzz.springfuse.core.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {

	@Test
	public void testCamel2Underscore() {
		String name1 = StringUtils.camel2Underscore("UserRole");
		assertEquals("user_role", name1);

		String name2 = StringUtils.camel2Underscore("UuuPppHhh");
		assertEquals("uuu_ppp_hhh", name2);
	}

	@Test
	public void testUnderscore2Camel() {
		String camelName = StringUtils.underscore2Camel("user_role");
		assertEquals("userRole", camelName);
	}

	@Test
	public void testCapitalize() {
		String capitalName = StringUtils.capitalize("userRole");
		assertEquals("UserRole", capitalName);
	}

}
