package com.github.herowzz.springfuse.api.dto.param;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "时间区间参数")
public class DateTimeBetweenParam {

	@ApiModelProperty("开始时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	protected LocalDateTime beginTime;

	@ApiModelProperty("结束时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	protected LocalDateTime endTime;

	public LocalDateTime getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(LocalDateTime beginTime) {
		this.beginTime = beginTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

}
