package com.github.herowzz.springfuse.example.generator;

import com.github.herowzz.springfuse.core.generator.SpringFuseGenerator;

public class SpringFuseGeneratorTest {

	public static void main(String[] args) {
		SpringFuseGenerator springFuseGenerator = new SpringFuseGenerator("com.github.herowzz.springfuse.example.domain", "E:\\temp\\2");
		springFuseGenerator.buildDao("com.github.herowzz.springfuse.example.dao.account");
		springFuseGenerator.buildService("com.github.herowzz.springfuse.example.service.account", "com.github.herowzz.springfuse.example.dao.account");
		//springFuseGenerator.buildController("com.github.herowzz.springfuse.example.controller", "com.github.herowzz.springfuse.example.service", "com.github.herowzz.springfuse.example.dto");
	}

}
