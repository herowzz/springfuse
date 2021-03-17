# SpringFuse

[![Build Status](https://travis-ci.org/herowzz/springfuse.svg?branch=master)](https://travis-ci.org/herowzz/springfuse) 
[![Maven](https://img.shields.io/maven-metadata/v/http/central.maven.org/maven2/com/github/herowzz/springfuse/maven-metadata.xml.svg?colorB=yellow)](https://oss.sonatype.org/#nexus-search;quick~springfuse)
![jdk1.15+](https://img.shields.io/badge/jdk-1.15%2B-orange.svg) 
![spring boot 2.4.3](https://img.shields.io/badge/spring%20boot-2.4.3-ff69b4.svg) 
![license](https://img.shields.io/hexpm/l/plug.svg)

SpringFuse Provide basic framework and class library based on Spring Boot Framework

## Useage
Add the following dependency to your pom.xml:
```xml
<dependency>
	<groupId>com.github.herowzz</groupId>
	<artifactId>springfuse</artifactId>
	<version>1.1.0-Release</version>
</dependency>
```  

You also can see the folder demo and this have a example project.

## Features
* JPA Enhance
	* Add BaseDao and BaseService to provide basic CURD method
	* Provide Soft-Delete feature
	* Entity class generate to DB Doc(html,word,pdf) file
* Rest Api Enhance
	* Unified the return ApiResult
	* Base ExceptionHandler to process exception
	* Add annotation @log to record the request log 
* Automatic code generation
* Some base core util class 