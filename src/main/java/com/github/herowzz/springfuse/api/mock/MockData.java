package com.github.herowzz.springfuse.api.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.github.jsonzou.jmockdata.JMockData;

public abstract class MockData {

	public static <T> T mockDto(Class<T> clazz) {
		return JMockData.mock(clazz);
	}

	public static <T> List<T> mockList(Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz));
		}
		return list;
	}

	public static <T> Page<T> mockPage(Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz));
		}
		Page<T> page = new PageImpl<T>(list, PageRequest.of(0, 10), 100);
		return page;
	}

	public static <T> Page<T> mockPage(Class<T> clazz, Pageable pageable) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz));
		}
		Page<T> page = new PageImpl<T>(list, pageable, 100);
		return page;
	}

}
