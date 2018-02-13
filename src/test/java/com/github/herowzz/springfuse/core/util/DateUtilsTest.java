package com.github.herowzz.springfuse.core.util;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void testDateToDateString() {
		String str1 = DateUtils.dateToDateString(new Date(), "yyyy-MM-dd");
		assertThat(str1.length(), is(10));

		String str2 = DateUtils.dateToDateString(null, "yyyy-MM-dd HH:mm");
		assertThat(str2.length(), is(16));

		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 5, 12, 15, 48);
		Date date = calendar.getTime();
		String str3 = DateUtils.dateToDateString(date, "yyyy-MM-dd HH:mm:ss");
		assertThat(str3.length(), is(19));
		assertThat(str3.substring(0, 4), is("2010"));
		assertThat(str3.substring(4, 5), is("-"));
		assertThat(str3.substring(5, 7), is("01"));
		assertThat(str3.substring(7, 8), is("-"));
		assertThat(str3.substring(8, 10), is("05"));
		assertThat(str3.substring(10, 11), is(" "));
		assertThat(str3.substring(11, 13), is("12"));
		assertThat(str3.substring(13, 14), is(":"));
		assertThat(str3.substring(14, 16), is("15"));
		assertThat(str3.substring(16, 17), is(":"));
		assertThat(str3.substring(17, 19), is("48"));
	}

	@Test
	public void testDateTimeToString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 5, 15, 8, 1, 59);
		Date date = calendar.getTime();
		String str3 = DateUtils.dateTimeToString(date);
		assertThat(str3.length(), is(19));
		assertThat(str3.substring(0, 4), is("2010"));
		assertThat(str3.substring(4, 5), is("-"));
		assertThat(str3.substring(5, 7), is("06"));
		assertThat(str3.substring(7, 8), is("-"));
		assertThat(str3.substring(8, 10), is("15"));
		assertThat(str3.substring(10, 11), is(" "));
		assertThat(str3.substring(11, 13), is("08"));
		assertThat(str3.substring(13, 14), is(":"));
		assertThat(str3.substring(14, 16), is("01"));
		assertThat(str3.substring(16, 17), is(":"));
		assertThat(str3.substring(17, 19), is("59"));
	}

	@Test
	public void testDateToString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 10, 1);
		Date date = calendar.getTime();
		String str3 = DateUtils.dateToString(date);
		assertThat(str3.length(), is(10));
		assertThat(str3.substring(0, 4), is("2015"));
		assertThat(str3.substring(4, 5), is("-"));
		assertThat(str3.substring(5, 7), is("11"));
		assertThat(str3.substring(7, 8), is("-"));
		assertThat(str3.substring(8, 10), is("01"));
	}

	@Test
	public void testDateToZhString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2015, 10, 1);
		Date date = calendar.getTime();
		String str3 = DateUtils.dateToZhString(date);
		assertThat(str3.length(), is(11));
		assertThat(str3.substring(0, 4), is("2015"));
		assertThat(str3.substring(4, 5), is("年"));
		assertThat(str3.substring(5, 7), is("11"));
		assertThat(str3.substring(7, 8), is("月"));
		assertThat(str3.substring(8, 10), is("01"));
		assertThat(str3.substring(10, 11), is("日"));
	}

	@Test
	public void testDateTimeToZhString() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 5, 15, 8, 1, 59);
		Date date = calendar.getTime();
		String str3 = DateUtils.dateTimeToZhString(date);
		assertThat(str3.length(), is(20));
		assertThat(str3.substring(0, 4), is("2010"));
		assertThat(str3.substring(4, 5), is("年"));
		assertThat(str3.substring(5, 7), is("06"));
		assertThat(str3.substring(7, 8), is("月"));
		assertThat(str3.substring(8, 10), is("15"));
		assertThat(str3.substring(10, 11), is("日"));
		assertThat(str3.substring(11, 13), is("08"));
		assertThat(str3.substring(13, 14), is("时"));
		assertThat(str3.substring(14, 16), is("01"));
		assertThat(str3.substring(16, 17), is("分"));
		assertThat(str3.substring(17, 19), is("59"));
		assertThat(str3.substring(19, 20), is("秒"));
	}

	@Test
	public void testDateToLocalDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15, 21, 50, 59);
		Date date = calendar.getTime();
		LocalDateTime localDateTime = DateUtils.dateToLocalDateTime(date);
		assertThat(localDateTime.getYear(), is(calendar.get(Calendar.YEAR)));
		assertThat(localDateTime.getMonthValue(), is(calendar.get(Calendar.MONTH) + 1));
		assertThat(localDateTime.getDayOfMonth(), is(calendar.get(Calendar.DAY_OF_MONTH)));
		assertThat(localDateTime.getHour(), is(calendar.get(Calendar.HOUR_OF_DAY)));
		assertThat(localDateTime.getMinute(), is(calendar.get(Calendar.MINUTE)));
		assertThat(localDateTime.getSecond(), is(calendar.get(Calendar.SECOND)));
	}

	@Test
	public void testDateToLocalDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 9, 1);
		Date date = calendar.getTime();
		LocalDate localDate = DateUtils.dateToLocalDate(date);
		assertThat(localDate.getYear(), is(calendar.get(Calendar.YEAR)));
		assertThat(localDate.getMonthValue(), is(calendar.get(Calendar.MONTH) + 1));
		assertThat(localDate.getDayOfMonth(), is(calendar.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void testLocalDateTimeToDate() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 1, 1, 23, 30, 1);
		Date date = DateUtils.localDateTimeToDate(localDateTime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertThat(localDateTime.getYear(), is(calendar.get(Calendar.YEAR)));
		assertThat(localDateTime.getMonthValue(), is(calendar.get(Calendar.MONTH) + 1));
		assertThat(localDateTime.getDayOfMonth(), is(calendar.get(Calendar.DAY_OF_MONTH)));
		assertThat(localDateTime.getHour(), is(calendar.get(Calendar.HOUR_OF_DAY)));
		assertThat(localDateTime.getMinute(), is(calendar.get(Calendar.MINUTE)));
		assertThat(localDateTime.getSecond(), is(calendar.get(Calendar.SECOND)));
	}

	@Test
	public void testLocalDateToDate() {
		LocalDate localDate = LocalDate.of(2010, 12, 30);
		Date date = DateUtils.localDateToDate(localDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		assertThat(localDate.getYear(), is(calendar.get(Calendar.YEAR)));
		assertThat(localDate.getMonthValue(), is(calendar.get(Calendar.MONTH) + 1));
		assertThat(localDate.getDayOfMonth(), is(calendar.get(Calendar.DAY_OF_MONTH)));
	}

	@Test
	public void testGetDayBeginLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 12, 11, 23, 30, 1);
		LocalDateTime dayBeginDateTime = DateUtils.getDayBegin(localDateTime);
		assertThat(dayBeginDateTime.getYear(), is(2010));
		assertThat(dayBeginDateTime.getMonthValue(), is(12));
		assertThat(dayBeginDateTime.getDayOfMonth(), is(11));
		assertThat(dayBeginDateTime.getHour(), is(0));
		assertThat(dayBeginDateTime.getMinute(), is(0));
		assertThat(dayBeginDateTime.getSecond(), is(0));
	}

	@Test
	public void testGetDayBeginDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15, 21, 50, 59);
		Date date = calendar.getTime();
		LocalDateTime dayBeginDateTime = DateUtils.getDayBegin(date);
		assertThat(dayBeginDateTime.getYear(), is(2010));
		assertThat(dayBeginDateTime.getMonthValue(), is(1));
		assertThat(dayBeginDateTime.getDayOfMonth(), is(15));
		assertThat(dayBeginDateTime.getHour(), is(0));
		assertThat(dayBeginDateTime.getMinute(), is(0));
		assertThat(dayBeginDateTime.getSecond(), is(0));

	}

	@Test
	public void testGetDayEndLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 12, 11, 11, 01, 15);
		LocalDateTime dayBeginDateTime = DateUtils.getDayEnd(localDateTime);
		assertThat(dayBeginDateTime.getYear(), is(2010));
		assertThat(dayBeginDateTime.getMonthValue(), is(12));
		assertThat(dayBeginDateTime.getDayOfMonth(), is(11));
		assertThat(dayBeginDateTime.getHour(), is(23));
		assertThat(dayBeginDateTime.getMinute(), is(59));
		assertThat(dayBeginDateTime.getSecond(), is(59));
	}

	@Test
	public void testGetDayEndDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15, 21, 50, 59);
		Date date = calendar.getTime();
		LocalDateTime dayBeginDateTime = DateUtils.getDayEnd(date);
		assertThat(dayBeginDateTime.getYear(), is(2010));
		assertThat(dayBeginDateTime.getMonthValue(), is(1));
		assertThat(dayBeginDateTime.getDayOfMonth(), is(15));
		assertThat(dayBeginDateTime.getHour(), is(23));
		assertThat(dayBeginDateTime.getMinute(), is(59));
		assertThat(dayBeginDateTime.getSecond(), is(59));
	}

	@Test
	public void testGetMonthFirstDayLocalDate() {
		LocalDate localDate = LocalDate.of(2010, 12, 30);
		LocalDate monthFirstDate = DateUtils.getMonthFirstDay(localDate);
		assertThat(monthFirstDate.getYear(), is(2010));
		assertThat(monthFirstDate.getMonthValue(), is(12));
		assertThat(monthFirstDate.getDayOfMonth(), is(1));
	}

	@Test
	public void testGetMonthFirstDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15);
		Date date = calendar.getTime();
		LocalDate monthFirstDate = DateUtils.getMonthFirstDay(date);
		assertThat(monthFirstDate.getYear(), is(2010));
		assertThat(monthFirstDate.getMonthValue(), is(1));
		assertThat(monthFirstDate.getDayOfMonth(), is(1));
	}

	@Test
	public void testGetMonthFirstDayTimeLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 12, 11, 11, 01, 15);
		LocalDateTime monthFirstDate = DateUtils.getMonthFirstDayTime(localDateTime);
		assertThat(monthFirstDate.getYear(), is(2010));
		assertThat(monthFirstDate.getMonthValue(), is(12));
		assertThat(monthFirstDate.getDayOfMonth(), is(1));
		assertThat(monthFirstDate.getHour(), is(0));
		assertThat(monthFirstDate.getMinute(), is(0));
		assertThat(monthFirstDate.getSecond(), is(0));
	}

	@Test
	public void testGetMonthFirstDayTimeDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15, 23, 44, 13);
		Date date = calendar.getTime();
		LocalDateTime monthFirstDate = DateUtils.getMonthFirstDayTime(date);
		assertThat(monthFirstDate.getYear(), is(2010));
		assertThat(monthFirstDate.getMonthValue(), is(1));
		assertThat(monthFirstDate.getDayOfMonth(), is(1));
		assertThat(monthFirstDate.getHour(), is(0));
		assertThat(monthFirstDate.getMinute(), is(0));
		assertThat(monthFirstDate.getSecond(), is(0));
	}

	@Test
	public void testGetMonthLastDayLocalDate() {
		LocalDate localDate = LocalDate.of(2010, 12, 1);
		LocalDate monthLastDate = DateUtils.getMonthLastDay(localDate);
		assertThat(monthLastDate.getYear(), is(2010));
		assertThat(monthLastDate.getMonthValue(), is(12));
		assertThat(monthLastDate.getDayOfMonth(), is(31));
	}

	@Test
	public void testGetMonthLastDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15);
		Date date = calendar.getTime();
		LocalDate monthLastDate = DateUtils.getMonthLastDay(date);
		assertThat(monthLastDate.getYear(), is(2010));
		assertThat(monthLastDate.getMonthValue(), is(1));
		assertThat(monthLastDate.getDayOfMonth(), is(31));
	}

	@Test
	public void testGetMonthLastDayTimeLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 12, 11, 11, 01, 15);
		LocalDateTime monthLastDate = DateUtils.getMonthLastDayTime(localDateTime);
		assertThat(monthLastDate.getYear(), is(2010));
		assertThat(monthLastDate.getMonthValue(), is(12));
		assertThat(monthLastDate.getDayOfMonth(), is(31));
		assertThat(monthLastDate.getHour(), is(23));
		assertThat(monthLastDate.getMinute(), is(59));
		assertThat(monthLastDate.getSecond(), is(59));
	}

	@Test
	public void testGetMonthLastDayTimeDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 15, 23, 44, 13);
		Date date = calendar.getTime();
		LocalDateTime monthLastDate = DateUtils.getMonthLastDayTime(date);
		assertThat(monthLastDate.getYear(), is(2010));
		assertThat(monthLastDate.getMonthValue(), is(1));
		assertThat(monthLastDate.getDayOfMonth(), is(31));
		assertThat(monthLastDate.getHour(), is(23));
		assertThat(monthLastDate.getMinute(), is(59));
		assertThat(monthLastDate.getSecond(), is(59));
	}

	@Test
	public void testGetYearFirstDayLocalDate() {
		LocalDate localDate = LocalDate.of(2010, 12, 15);
		LocalDate yearFirstDate = DateUtils.getYearFirstDay(localDate);
		assertThat(yearFirstDate.getYear(), is(2010));
		assertThat(yearFirstDate.getMonthValue(), is(1));
		assertThat(yearFirstDate.getDayOfMonth(), is(1));
	}

	@Test
	public void testGetYearFirstDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 11, 15);
		Date date = calendar.getTime();
		LocalDate yearFirstDate = DateUtils.getYearFirstDay(date);
		assertThat(yearFirstDate.getYear(), is(2010));
		assertThat(yearFirstDate.getMonthValue(), is(1));
		assertThat(yearFirstDate.getDayOfMonth(), is(1));
	}

	@Test
	public void testGetYearFirstDayTimeLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 12, 11, 11, 01, 15);
		LocalDateTime yearFirstDate = DateUtils.getYearFirstDayTime(localDateTime);
		assertThat(yearFirstDate.getYear(), is(2010));
		assertThat(yearFirstDate.getMonthValue(), is(1));
		assertThat(yearFirstDate.getDayOfMonth(), is(1));
		assertThat(yearFirstDate.getHour(), is(0));
		assertThat(yearFirstDate.getMinute(), is(0));
		assertThat(yearFirstDate.getSecond(), is(0));
	}

	@Test
	public void testGetYearFirstDayTimeDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 11, 15, 23, 59, 59);
		Date date = calendar.getTime();
		LocalDateTime yearFirstDate = DateUtils.getYearFirstDayTime(date);
		assertThat(yearFirstDate.getYear(), is(2010));
		assertThat(yearFirstDate.getMonthValue(), is(1));
		assertThat(yearFirstDate.getDayOfMonth(), is(1));
		assertThat(yearFirstDate.getHour(), is(0));
		assertThat(yearFirstDate.getMinute(), is(0));
		assertThat(yearFirstDate.getSecond(), is(0));
	}

	@Test
	public void testGetYearLastDayLocalDate() {
		LocalDate localDate = LocalDate.of(2010, 1, 15);
		LocalDate yearLastDate = DateUtils.getYearLastDay(localDate);
		assertThat(yearLastDate.getYear(), is(2010));
		assertThat(yearLastDate.getMonthValue(), is(12));
		assertThat(yearLastDate.getDayOfMonth(), is(31));
	}

	@Test
	public void testGetYearLastDayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 11, 15);
		Date date = calendar.getTime();
		LocalDate yearLastDate = DateUtils.getYearLastDay(date);
		assertThat(yearLastDate.getYear(), is(2010));
		assertThat(yearLastDate.getMonthValue(), is(12));
		assertThat(yearLastDate.getDayOfMonth(), is(31));
	}

	@Test
	public void testGetYearLastDayTimeLocalDateTime() {
		LocalDateTime localDateTime = LocalDateTime.of(2010, 3, 11, 11, 01, 15);
		LocalDateTime yearLastDate = DateUtils.getYearLastDayTime(localDateTime);
		assertThat(yearLastDate.getYear(), is(2010));
		assertThat(yearLastDate.getMonthValue(), is(12));
		assertThat(yearLastDate.getDayOfMonth(), is(31));
		assertThat(yearLastDate.getHour(), is(23));
		assertThat(yearLastDate.getMinute(), is(59));
		assertThat(yearLastDate.getSecond(), is(59));
	}

	@Test
	public void testGetYearLastDayTimeDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 11, 15, 1, 1, 1);
		Date date = calendar.getTime();
		LocalDateTime yearLastDate = DateUtils.getYearLastDayTime(date);
		assertThat(yearLastDate.getYear(), is(2010));
		assertThat(yearLastDate.getMonthValue(), is(12));
		assertThat(yearLastDate.getDayOfMonth(), is(31));
		assertThat(yearLastDate.getHour(), is(23));
		assertThat(yearLastDate.getMinute(), is(59));
		assertThat(yearLastDate.getSecond(), is(59));
	}

	@Test
	public void testIsAfterTodayLocalDateTime() {
		assertThat(DateUtils.isAfterToday(LocalDateTime.of(2900, 1, 1, 1, 1)), is(true));
		assertThat(DateUtils.isAfterToday(LocalDateTime.of(2000, 1, 1, 1, 1)), is(false));

		LocalDateTime now = LocalDateTime.now();
		assertThat(DateUtils.isAfterToday(now.minusDays(1)), is(false));
		assertThat(DateUtils.isAfterToday(now.plusDays(1)), is(true));
	}

	@Test
	public void testIsAfterTodayLocalDate() {
		assertThat(DateUtils.isAfterToday(LocalDate.of(2900, 1, 1)), is(true));
		assertThat(DateUtils.isAfterToday(LocalDate.of(2000, 1, 1)), is(false));

		LocalDate now = LocalDate.now();
		assertThat(DateUtils.isAfterToday(now.minusDays(1)), is(false));
		assertThat(DateUtils.isAfterToday(now.plusDays(1)), is(true));
	}

	@Test
	public void testIsBeforeTodayLocalDateTime() {
		assertThat(DateUtils.isBeforeToday(LocalDateTime.of(2900, 1, 1, 1, 1)), is(false));
		assertThat(DateUtils.isBeforeToday(LocalDateTime.of(2000, 1, 1, 1, 1)), is(true));

		LocalDateTime now = LocalDateTime.now();
		assertThat(DateUtils.isBeforeToday(now.minusDays(1)), is(true));
		assertThat(DateUtils.isBeforeToday(now.plusDays(1)), is(false));
	}

	@Test
	public void testIsBeforeTodayLocalDate() {
		assertThat(DateUtils.isBeforeToday(LocalDate.of(2900, 1, 1)), is(false));
		assertThat(DateUtils.isBeforeToday(LocalDate.of(2000, 1, 1)), is(true));

		LocalDate now = LocalDate.now();
		assertThat(DateUtils.isBeforeToday(now.minusDays(1)), is(true));
		assertThat(DateUtils.isBeforeToday(now.plusDays(1)), is(false));
	}

	@Test
	public void testGetDateTimeFormatLocalDateTimeString() {
		String str = DateUtils.getDateTimeFormat(LocalDateTime.of(2010, 1, 1, 1, 1, 1), "yyyy-MM-dd HH:mm:ss");
		assertThat(str.length(), is(19));
		assertThat(str.substring(0, 4), is("2010"));
		assertThat(str.substring(4, 5), is("-"));
		assertThat(str.substring(5, 7), is("01"));
		assertThat(str.substring(7, 8), is("-"));
		assertThat(str.substring(8, 10), is("01"));
		assertThat(str.substring(10, 11), is(" "));
		assertThat(str.substring(11, 13), is("01"));
		assertThat(str.substring(13, 14), is(":"));
		assertThat(str.substring(14, 16), is("01"));
		assertThat(str.substring(16, 17), is(":"));
		assertThat(str.substring(17, 19), is("01"));
	}

	@Test
	public void testGetDateTimeInFormatLocalDateTime() {
		String str = DateUtils.getDateTimeInFormat(LocalDateTime.of(2010, 1, 1, 1, 1, 1));
		assertThat(str.length(), is(14));
		assertThat(str.substring(0, 4), is("2010"));
		assertThat(str.substring(4, 6), is("01"));
		assertThat(str.substring(6, 8), is("01"));
		assertThat(str.substring(8, 10), is("01"));
		assertThat(str.substring(10, 12), is("01"));
		assertThat(str.substring(12, 14), is("01"));
	}

	@Test
	public void testGetNowDateTimeInFormat() {
		String str = DateUtils.getNowDateTimeInFormat();
		assertThat(str.length(), is(14));
	}

}
