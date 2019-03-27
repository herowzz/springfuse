package com.github.herowzz.springfuse.core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.apache.commons.text.RandomStringGenerator;

/**
 * Code生成工具类
 * @author wangzz
 */
public abstract class CodeUtils {

	/**
	 * 返回指定长度的数字随机数
	 * @param length 长度
	 * @return 数字随机数(0到9)
	 */
	public static String getRandomIntCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').build();
		return generator.generate(length);
	}

	/**
	 * 返回指定长度的字母随机数
	 * @param length 长度
	 * @return 字母随机数(a到z)
	 */
	public static String getRandomAbcCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
		return generator.generate(length);
	}

	/**
	 * 返回指定长度的数字和字母随机数
	 * @param length 长度
	 * @return 数字和字母随机数(a到z)
	 */
	public static String getRandomStrCode(int length) {
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').withinRange('a', 'z').build();
		return generator.generate(length);
	}

	/**
	 * 返回UUID字符串(不带横杠 - )
	 * @return 不带横杠的UUID字符串
	 */
	public static String getUUIDStr() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 返回当前时间字符串(年月日时分秒毫秒)
	 * @return 返回当前时间年月日时分秒字符串
	 */
	public static String getNowDateStr() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
	}
	
	/**
	 * 返回当前时间字符串
	 * @return 返回当前时间字符串
	 */
	public static String getNowDateStr(String format) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	}

	/**
	 * 返回当前时间字符串
	 * @return 返回当前时间字符串
	 */
	public static String getNowDateStr(String prefix, String format) {
		return prefix + LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
	}

}
