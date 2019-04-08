package com.github.herowzz.springfuse.api.mock;

import java.time.LocalDateTime;

import com.github.jsonzou.jmockdata.DataConfig;
import com.github.jsonzou.jmockdata.Mocker;

public class LocalDateTimeMock implements Mocker<Object> {


	@Override
	public Object mock(DataConfig mockConfig) {
		return LocalDateTime.now();
	}

}
