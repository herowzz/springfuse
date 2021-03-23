package com.github.herowzz.springfuse.api.valid;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.github.herowzz.springfuse.api.dto.param.DateTimeBetweenParam;

public class DateTimeBetweenValidator implements ConstraintValidator<DateTimeBetweenValid, DateTimeBetweenParam> {

	private long intervalDays;

	@Override
	public void initialize(DateTimeBetweenValid dateTimeBetweenValid) {
		this.intervalDays = dateTimeBetweenValid.intervalDays();
	}

	@Override
	public boolean isValid(DateTimeBetweenParam param, ConstraintValidatorContext context) {
		LocalDateTime beginTime = param.getBeginTime();
		LocalDateTime endTime = param.getEndTime();
		if (beginTime == null || endTime == null)
			return true;
		if (!beginTime.isBefore(endTime))
			return false;
		Duration duration = Duration.between(beginTime, endTime);
		long seconds = duration.toSeconds();
		if (seconds > intervalDays * 24 * 60 * 60)
			return false;
		return true;
	}

}
