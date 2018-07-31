package com.github.herowzz.springfuse.example.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.github.herowzz.springfuse.api.log.ILogManage;
import com.github.herowzz.springfuse.api.log.LogRecord;
import com.github.herowzz.springfuse.data.jpa.dao.IBaseDao;
import com.github.herowzz.springfuse.data.jpa.service.BaseService;
import com.github.herowzz.springfuse.example.dao.system.LogInfoDao;
import com.github.herowzz.springfuse.example.domain.system.LogInfo;

@Service
public class LogInfoService extends BaseService<LogInfo, String> implements ILogManage {

	@Autowired
	private LogInfoDao logInfoDao;

	@Override
	protected IBaseDao<LogInfo, String> getEntityDao() {
		return logInfoDao;
	}

	@Async
	@Override
	public void save(LogRecord record) {
		LogInfo info = new LogInfo();
		info.setCreateTime(record.getCreateTime());
		info.setMessage(record.getMessage());
		info.setModule(record.getModule());
		info.setParamInfo(record.getParamInfo());
		info.setResultInfo(record.getResultInfo());
		info.setUserId(record.getUserId());
		info.setUserName(record.getUserName());
		logInfoDao.save(info);
	}

}
