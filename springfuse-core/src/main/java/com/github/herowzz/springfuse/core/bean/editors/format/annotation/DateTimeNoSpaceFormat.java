package com.github.herowzz.springfuse.core.bean.editors.format.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Format date to String by yyyyMMddHHmmss
 * @author wangzz
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@DateTimeFormat(pattern = "yyyyMMddHHmmss")
public @interface DateTimeNoSpaceFormat {

	@AliasFor(annotation = DateTimeFormat.class)
	String pattern() default "";

}
