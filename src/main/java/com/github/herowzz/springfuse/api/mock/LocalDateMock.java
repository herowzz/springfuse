package com.github.herowzz.springfuse.api.mock;

import java.time.LocalDate;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;

public class LocalDateMock implements Mocker<Object> {

	@Override
	public Object mock(DataConfig mockConfig) {
		return LocalDate.now();
	}

}
