package com.github.herowzz.springfuse.api.log.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@Documented
public @interface Log {

	/**
	 * 模块名
	 * @return 模块名
	 */
	String name() default "";

	/**
	 * 记录内容
	 * @return 记录内容
	 */
	String value() default "";
}
