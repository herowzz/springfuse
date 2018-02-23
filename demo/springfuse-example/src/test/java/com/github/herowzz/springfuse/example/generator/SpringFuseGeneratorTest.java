package com.github.herowzz.springfuse.example.generator;

import com.github.herowzz.springfuse.core.generator.SpringFuseGenerator;

public class SpringFuseGeneratorTest {

	public static void main(String[] args) {
		SpringFuseGenerator springFuseGenerator = new SpringFuseGenerator("com.github.herowzz.springfuse.example.domain", "E:\\temp");
		springFuseGenerator.buildDto("com.github.herowzz.springfuse.example.dto");

	}

}
