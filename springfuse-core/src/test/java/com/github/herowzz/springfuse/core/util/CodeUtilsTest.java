package com.github.herowzz.springfuse.core.util;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class CodeUtilsTest {

	/**
	 * 根据传入的计算次数进行多次计算随机数,然后判断这些生成的随机数最多有多少个重复的
	 * @param supplier 计算方法,返回生成的随机数
	 * @param countNum 需要循环计算的次数
	 * @return 最多的重复个数
	 */
	private long calculateRandomCount(Supplier<String> supplier, int countNum) {
		Collector<String, ?, Map<String, Long>> collector = Collectors.groupingBy(Function.identity(), Collectors.counting());
		return IntStream.rangeClosed(1, countNum).mapToObj(s -> supplier.get()).collect(collector).values().stream().max(Long::compare).get().longValue();
	}

	@Test
	public void testCalculateRandomCount() {
		String[] testStrs = { "a", "b", "c" };
		AtomicInteger ai1 = new AtomicInteger(0);
		long calculateCount1 = calculateRandomCount(() -> testStrs[ai1.getAndIncrement() % 3], 4);
		assertThat(calculateCount1, is(2L));

		AtomicInteger ai2 = new AtomicInteger(0);
		long calculateCount2 = calculateRandomCount(() -> testStrs[ai2.getAndIncrement() % 3], 6);
		assertThat(calculateCount2, is(2L));

		AtomicInteger ai3 = new AtomicInteger(0);
		long calculateCount3 = calculateRandomCount(() -> testStrs[ai3.getAndIncrement() % 3], 7);
		assertThat(calculateCount3, is(3L));

		AtomicInteger ai4 = new AtomicInteger(0);
		long calculateCount4 = calculateRandomCount(() -> testStrs[ai4.getAndIncrement() % 3], 10);
		assertThat(calculateCount4, is(4L));
	}

	@Test
	public void testGetRandomIntCode() {
		String result = CodeUtils.getRandomIntCode(10);
		assertThat(result.length(), is(10));
		for (char c : result.toCharArray()) {
			assertThat(Character.isDigit(c), is(true));
		}

		String result2 = CodeUtils.getRandomIntCode(5);
		assertThat(result2.length(), not(10));
		assertThat(result2.matches("[a-zA-Z]+"), is(false));

		long calculateCount = calculateRandomCount(() -> CodeUtils.getRandomIntCode(5), 100);
		assertThat(calculateCount < 10, is(true));
	}

	@Test
	public void testGetRandomAbcCode() {
		String result = CodeUtils.getRandomAbcCode(10);
		assertThat(result.length(), not(5));
		for (char c : result.toCharArray()) {
			assertThat(Character.isDigit(c), is(false));
		}

		String result2 = CodeUtils.getRandomAbcCode(5);
		assertThat(result2.length(), is(5));
		assertThat(result2.matches("[a-zA-Z]+"), is(true));
		for (char c : result2.toCharArray()) {
			assertThat(Character.isDigit(c), is(false));
		}

		long calculateCount = calculateRandomCount(() -> CodeUtils.getRandomAbcCode(5), 100);
		assertThat(calculateCount < 10, is(true));
	}

	@Test
	public void testGetRandomStrCode() {
		String result = CodeUtils.getRandomStrCode(20);
		assertThat(result.length(), is(20));

		String result2 = CodeUtils.getRandomIntCode(5);
		assertThat(result2.length(), not(10));

		for (char c : result.toCharArray()) {
			boolean isDigOrAbc = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z');
			assertThat(isDigOrAbc, is(true));
		}

		long calculateCount = calculateRandomCount(() -> CodeUtils.getRandomStrCode(5), 100);
		assertThat(calculateCount < 10, is(true));
	}

	@Test
	public void testGetUUIDStr() {
		String result = CodeUtils.getUUIDStr();
		assertThat(result.length(), is(32));

		for (char c : result.toCharArray()) {
			assertThat(c, not('-'));
		}

		long calculateCount = calculateRandomCount(() -> CodeUtils.getUUIDStr(), 5);
		assertThat(calculateCount == 1, is(true));
	}

}
