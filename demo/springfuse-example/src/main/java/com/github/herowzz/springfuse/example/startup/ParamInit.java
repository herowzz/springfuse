package com.github.herowzz.springfuse.example.startup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.github.herowzz.springfuse.config.ConfigParam;

@Component
public class ParamInit implements ApplicationRunner {

	@Value("${config.page.size}")
	private int pageSize;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		ConfigParam.pageSize = pageSize;
	}

}
