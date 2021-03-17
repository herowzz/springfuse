package com.github.herowzz.springfuse.api.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;

public abstract class MockData {

	static MockConfig config = new MockConfig();

	static {
		config.intRange(0, 2);
	}

	public static <T> T mockDto(Class<T> clazz) {
		return JMockData.mock(clazz, config);
	}

	public static <T> List<T> mockList(Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz, config));
		}
		return list;
	}

	public static <T> Page<T> mockPage(Class<T> clazz) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz, config));
		}
		Page<T> page = new PageImpl<T>(list, PageRequest.of(0, 10), 100);
		return page;
	}

	public static <T> Page<T> mockPage(Class<T> clazz, Pageable pageable) {
		List<T> list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list.add(JMockData.mock(clazz, config));
		}
		Page<T> page = new PageImpl<T>(list, pageable, 100);
		return page;
	}

}
