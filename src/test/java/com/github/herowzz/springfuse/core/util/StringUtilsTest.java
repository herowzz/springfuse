package com.github.herowzz.springfuse.core.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class StringUtilsTest {

	@Test
	public void testCamel2Underscore() {
		String name1 = StringUtils.camel2Underscore("UserRole");
		assertThat(name1, is("user_role"));

		String name2 = StringUtils.camel2Underscore("UuuPppHhh");
		assertThat(name2, is("uuu_ppp_hhh"));
	}

	@Test
	public void testUnderscore2Camel() {
		String camelName = StringUtils.underscore2Camel("user_role");
		assertThat(camelName, is("userRole"));
	}

	@Test
	public void testCapitalize() {
		String capitalName = StringUtils.capitalize("userRole");
		assertThat(capitalName, is("UserRole"));
	}

}
