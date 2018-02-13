package com.github.herowzz.springfuse.core.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author wangzz
 */
public abstract class DateUtils {

	public static final String DATA_FORMAT = "yyyy-MM-dd";
	public static final String DATATIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATA_ZH_FORMAT = "yyyy年MM月dd日";
	public static final String DATATIMEF_ZH_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String DATATIMEF_IN_FORMAT = "yyyyMMddHHmmss";

	/**
	 * 将date类型转换成字符串(若date为空则返回当前时间)
	 * 
	 * @param date 需要转换的date类型
	 * @param formatStr 转换格式
	 * @return 格式化后的字符串
	 */
	public static String dateToDateString(Date date, String formatStr) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat(formatStr).format(date);
	}

	/**
	 * 将date类型转换成字符串'yyyy-MM-dd HH:mm:ss'格式
	 */
	public static String dateTimeToString(Date date) {
		return dateToDateString(date, DATATIMEF_FORMAT);
	}

	/**
	 * 将date类型转换成字符串'yyyy-MM-dd'格式
	 */
	public static String dateToString(Date date) {
		return dateToDateString(date, DATA_FORMAT);
	}

	/**
	 * 将date类型转换成字符串'yyyy年MM月dd日'格式
	 */
	public static String dateToZhString(Date date) {
		return dateToDateString(date, DATA_ZH_FORMAT);
	}

	/**
	 * 将date类型转换成字符串'yyyy年MM月dd日HH时mm分ss秒'格式
	 */
	public static String dateTimeToZhString(Date date) {
		return dateToDateString(date, DATATIMEF_ZH_FORMAT);
	}

	/**
	 * date类型转换成LocalDateTime类型
	 * 
	 * @param date 需要转换的date类型
	 * @return 转换后的LocalDateTime类型
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	/**
	 * date类型转换成LocalDate类型
	 * 
	 * @param date 需要转换的date类型
	 * @return 转换后的LocalDate类型
	 */
	public static LocalDate dateToLocalDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}

	/**
	 * LocalDateTime类型转换成Date类型
	 * 
	 * @param localDateTime 需要转换的LocalDateTime类型
	 * @return 转换后的Date类型
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * LocalDate类型转换成Date类型
	 * 
	 * @param localDate 需要转换的LocalDate类型
	 * @return 转换后的Date类型
	 */
	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 获取给定日期的当日开始时间（0点0时0秒）
	 */
	public static LocalDateTime getDayBegin(LocalDateTime dt) {
		if (dt == null)
			dt = LocalDateTime.now();
		return dt.toLocalDate().atStartOfDay();
	}

	/**
	 * 获取给定日期的当日开始时间（0点0时0秒）
	 */
	public static LocalDateTime getDayBegin(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getDayBegin(dt);
	}

	/**
	 * 获取给定日期的当日最后时间（23点59时59秒9999）
	 */
	public static LocalDateTime getDayEnd(LocalDateTime dt) {
		if (dt == null)
			dt = LocalDateTime.now();
		return LocalDateTime.of(dt.toLocalDate(), LocalTime.MAX);
	}

	/**
	 * 获取给定日期的当日最后时间（23点59时59秒9999）
	 */
	public static LocalDateTime getDayEnd(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getDayEnd(dt);
	}

	/**
	 * 获取给定日期所属当月第一天
	 */
	public static LocalDate getMonthFirstDay(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		return date.withDayOfMonth(1);
	}

	/**
	 * 获取给定日期所属当月第一天
	 */
	public static LocalDate getMonthFirstDay(Date date) {
		LocalDate dt = LocalDate.now();
		if (date != null)
			dt = dateToLocalDate(date);
		return getMonthFirstDay(dt);
	}

	/**
	 * 获取给定日期所属当月第一天00:00:00
	 */
	public static LocalDateTime getMonthFirstDayTime(LocalDateTime date) {
		if (date == null)
			date = LocalDateTime.now();
		return getMonthFirstDay(date.toLocalDate()).atStartOfDay();
	}

	/**
	 * 获取给定日期所属当月第一天00:00:00
	 */
	public static LocalDateTime getMonthFirstDayTime(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getMonthFirstDayTime(dt);
	}

	/**
	 * 获取给定日期所属当月最后一天
	 */
	public static LocalDate getMonthLastDay(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		return date.with(TemporalAdjusters.lastDayOfMonth());
	}

	/**
	 * 获取给定日期所属当月最后一天
	 */
	public static LocalDate getMonthLastDay(Date date) {
		LocalDate dt = LocalDate.now();
		if (date != null)
			dt = dateToLocalDate(date);
		return getMonthLastDay(dt);
	}

	/**
	 * 获取给定日期所属当月最后一天23:59:59
	 */
	public static LocalDateTime getMonthLastDayTime(LocalDateTime date) {
		if (date == null)
			date = LocalDateTime.now();
		return LocalDateTime.of(getMonthLastDay(date.toLocalDate()), LocalTime.MAX);
	}

	/**
	 * 获取给定日期所属当月最后一天23:59:59
	 */
	public static LocalDateTime getMonthLastDayTime(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getMonthLastDayTime(dt);
	}

	/**
	 * 获取给定日期所属当年第一天
	 */
	public static LocalDate getYearFirstDay(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		return date.withDayOfYear(1);
	}

	/**
	 * 获取给定日期所属当年第一天
	 */
	public static LocalDate getYearFirstDay(Date date) {
		LocalDate dt = LocalDate.now();
		if (date != null)
			dt = dateToLocalDate(date);
		return getYearFirstDay(dt);
	}

	/**
	 * 获取给定日期所属当年第一天00:00:00
	 */
	public static LocalDateTime getYearFirstDayTime(LocalDateTime date) {
		if (date == null)
			date = LocalDateTime.now();
		return getYearFirstDay(date.toLocalDate()).atStartOfDay();
	}

	/**
	 * 获取给定日期所属当年第一天00:00:00
	 */
	public static LocalDateTime getYearFirstDayTime(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getYearFirstDayTime(dt);
	}

	/**
	 * 获取给定日期所属当年最后一天
	 */
	public static LocalDate getYearLastDay(LocalDate date) {
		if (date == null)
			date = LocalDate.now();
		return date.with(TemporalAdjusters.lastDayOfYear());
	}

	/**
	 * 获取给定日期所属当年最后一天
	 */
	public static LocalDate getYearLastDay(Date date) {
		LocalDate dt = LocalDate.now();
		if (date != null)
			dt = dateToLocalDate(date);
		return getYearLastDay(dt);
	}

	/**
	 * 获取给定日期所属当年最后一天23:59:59
	 */
	public static LocalDateTime getYearLastDayTime(LocalDateTime date) {
		if (date == null)
			date = LocalDateTime.now();
		return LocalDateTime.of(getYearLastDay(date.toLocalDate()), LocalTime.MAX);
	}

	/**
	 * 获取给定日期所属当年最后一天23:59:59
	 */
	public static LocalDateTime getYearLastDayTime(Date date) {
		LocalDateTime dt = LocalDateTime.now();
		if (date != null)
			dt = dateToLocalDateTime(date);
		return getYearLastDayTime(dt);
	}

	/**
	 * 判断给定日期是否在当前日期后
	 */
	public static boolean isAfterToday(LocalDateTime date) {
		LocalDateTime today = LocalDateTime.now();
		return date.isAfter(today);
	}

	/**
	 * 判断给定日期是否在当前日期后
	 */
	public static boolean isAfterToday(LocalDate date) {
		LocalDate today = LocalDate.now();
		return date.isAfter(today);
	}

	/**
	 * 判断给定日期是否在当前日期前
	 */
	public static boolean isBeforeToday(LocalDateTime date) {
		LocalDateTime today = LocalDateTime.now();
		return date.isBefore(today);
	}

	/**
	 * 判断给定日期是否在当前日期前
	 */
	public static boolean isBeforeToday(LocalDate date) {
		LocalDate today = LocalDate.now();
		return date.isBefore(today);
	}

	/**
	 * 获取给定日期的格式化字符串
	 * @param date LocalDateTime日期
	 * @param format 需要返回的格式
	 * @return 格式化字符串
	 */
	public static String getDateTimeFormat(LocalDateTime date, String format) {
		return date.format(DateTimeFormatter.ofPattern(format));
	}

	/**
	 * 获取给定日期的格式化字符串<br>
	 * 格式化形式为:yyyyMMddHHmmss
	 * @param date LocalDateTime日期
	 * @return 格式化字符串
	 */
	public static String getDateTimeInFormat(LocalDateTime date) {
		return getDateTimeFormat(date, DATATIMEF_IN_FORMAT);
	}

	/**
	 * 获取当前日期的格式化字符串<br>
	 * 格式化形式为:yyyyMMddHHmmss
	 * @return 格式化字符串
	 */
	public static String getNowDateTimeInFormat() {
		return getDateTimeInFormat(LocalDateTime.now());
	}

}
