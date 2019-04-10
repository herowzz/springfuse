package com.github.herowzz.springfuse.api.mock;

import java.time.LocalDate;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.Mocker;

public class LocalDateMock implements Mocker<Object> {

	@Override
	public Object mock(DataConfig mockConfig) {
		MockConfig monthConfig = new MockConfig().intRange(1, 12);
		MockConfig dayConfig = new MockConfig().intRange(1, 28);
		int year = LocalDate.now().getYear();
		int month = JMockData.mock(int.class, monthConfig);
		int day = JMockData.mock(int.class, dayConfig);
		return LocalDate.of(year, month, day);
	}

}
