package com.github.herowzz.springfuse.api.log;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.dto.param.IBaseParam;
import com.github.herowzz.springfuse.api.log.annotation.Log;
import com.github.herowzz.springfuse.data.domain.BaseUser;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;

@Aspect
public class LogAspect {

	private ILogManage logManage;

	@AfterReturning(value = "@annotation(log) && (@within(org.springframework.web.bind.annotation.RestController) || @within(org.springframework.stereotype.Controller))", returning = "result")
	public void log(JoinPoint joinPoint, Log log, Object result) {
		if (!(result instanceof ApiResult))
			return;
		ApiResult<?> resObj = (ApiResult<?>) result;
		if (resObj.result != 1)
			return;

		Object[] parames = joinPoint.getArgs();
		for (Object param : parames) {
			if (param instanceof IBaseParam) {
				IBaseParam baseParam = (IBaseParam) param;
				LogRecord record = new LogRecord();
				record.setCreateTime(LocalDateTime.now());
				if (UserSessionManager.getUser() != null) {
					BaseUser user = UserSessionManager.getUser();
					record.setUserId(user.getId());
					record.setUserName(user.getRealname());
				}
				record.setModule(log.name());
				record.setMessage(log.value());
				record.setParamInfo(baseParam.toJson());
				record.setResultInfo(resObj.toJson());
				logManage.save(record);
			}
		}
	}

	public ILogManage getLogManage() {
		return logManage;
	}

	public void setLogManage(ILogManage logManage) {
		this.logManage = logManage;
	}

}
