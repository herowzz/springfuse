package com.github.herowzz.springfuse.api.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.Mocker;

public class LocalDateTimeMock implements Mocker<Object> {

	@Override
	public Object mock(DataConfig mockConfig) {
		MockConfig monthConfig = new MockConfig().intRange(1, 12);
		MockConfig dayConfig = new MockConfig().intRange(1, 28);
		MockConfig hourConfig = new MockConfig().intRange(1, 23);
		MockConfig minutsConfig = new MockConfig().intRange(1, 59);
		MockConfig secondConfig = new MockConfig().intRange(1, 59);
		int year = LocalDate.now().getYear();
		int month = JMockData.mock(int.class, monthConfig);
		int day = JMockData.mock(int.class, dayConfig);
		int hour = JMockData.mock(int.class, hourConfig);
		int minuts = JMockData.mock(int.class, minutsConfig);
		int second = JMockData.mock(int.class, secondConfig);
		return LocalDateTime.of(year, month, day, hour, minuts, second);
	}

}
