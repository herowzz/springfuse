# SpringFuse

[![Build Status](https://travis-ci.org/herowzz/springfuse.svg?branch=master)](https://travis-ci.org/herowzz/springfuse) ![jdk1.8+](https://img.shields.io/badge/jdk-1.8%2B-orange.svg) ![spring boot 2.0.4](https://img.shields.io/badge/spring%20boot-2.0.4-brightgreen.svg) ![license](https://img.shields.io/hexpm/l/plug.svg)

SpringFuse Provide basic framework and class library based on Spring Boot Framework

## Useage
Add the following dependency to your pom.xml:
```xml
<dependency>
	<groupId>com.github.herowzz</groupId>
	<artifactId>springfuse</artifactId>
	<version>1.0.0-RELEASE</version>
</dependency>
```  

You also can see the folder demo and this have a example project.

## Features
* JPA Enhance
	* Add BaseDao and BaseService to provide basic CURD method
	* Provide Soft-Delete feature
	* Entity class generate to doc(html,word,pdf) file
* Rest Api Enhance
	* Unified the return ApiResult
	* Base ExceptionHandler to process exception
	* Add annotation @log to record the request log 
* Automatic code generation
* Some base core util class 