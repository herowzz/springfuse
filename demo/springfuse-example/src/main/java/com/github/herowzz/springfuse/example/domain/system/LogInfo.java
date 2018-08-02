package com.github.herowzz.springfuse.example.domain.system;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.github.herowzz.springfuse.data.domain.annotation.Comment;

@Entity(name = "sys_loginfo")
@Table(indexes = { @Index(name = "IDX_LOG_USERID", columnList = "userId"), @Index(name = "IDX_LOG_DATE", columnList = "createTime") })
public class LogInfo implements Serializable {

	private static final long serialVersionUID = 3332286354337118369L;

	@Id
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@GeneratedValue(generator = "uuid")
	@Comment("主键,UUID")
	protected String id;

	@Comment("操作用户id")
	private String userId;

	@Comment("操作用户名")
	private String userName;

	@Comment("模块")
	private String module;

	@Comment("操作内容")
	private String message;

	@Column(length = 3000)
	@Comment("请求参数")
	private String paramInfo;

	@Comment("创建时间")
	private LocalDateTime createTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParamInfo() {
		return paramInfo;
	}

	public void setParamInfo(String paramInfo) {
		this.paramInfo = paramInfo;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
