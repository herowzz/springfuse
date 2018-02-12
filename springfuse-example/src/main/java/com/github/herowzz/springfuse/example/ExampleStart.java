package com.github.herowzz.springfuse.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExampleStart {

	public static void main(String[] args) {
		SpringApplication.run(ExampleStart.class, args);
	}

}
