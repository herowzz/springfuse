package com.github.herowzz.springfuse.api.valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = DateTimeBetweenValidator.class)
public @interface DateTimeBetweenValid {

	long intervalDays();

	String message() default "结束时间必须大于开始时间，并且不能超过{intervalDays}天";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}