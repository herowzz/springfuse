package com.github.herowzz.springfuse.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.github.herowzz.springfuse.security.manager.UserSessionManager;

/**
 * 实体类基类,提供常用基础参数
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建时间
	 */
	@Column(updatable = false, nullable = false)
	protected LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@Column(name = "create_user_id", updatable = false)
	protected String createUserId;

	/**
	 * 创建人姓名
	 */
	@Column(name = "create_user_name", updatable = false)
	protected String createUserName;

	/**
	 * 修改人ID
	 */
	@Column(name = "update_user_id")
	protected String updateUserId;

	/**
	 * 修改人姓名
	 */
	@Column(name = "update_user_name")
	protected String updateUserName;

	/**
	 * 修改时间
	 */
	protected LocalDateTime updateTime;

	@PrePersist
	protected void onCreate() {
		this.createTime = LocalDateTime.now();
		BaseUser user = UserSessionManager.getUser();
		if (user != null) {
			this.createUserId = user.getId();
			this.createUserName = user.getRealname();
		}
	}

	@PreUpdate
	protected void onUpdate() {
		this.updateTime = LocalDateTime.now();
		BaseUser user = UserSessionManager.getUser();
		if (user != null) {
			this.updateUserId = user.getId();
			this.updateUserName = user.getRealname();
		}
	}

	protected LocalDateTime getCreateTime() {
		return createTime;
	}

	protected void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	protected String getCreateUserId() {
		return createUserId;
	}

	protected void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	protected String getCreateUserName() {
		return createUserName;
	}

	protected void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	protected String getUpdateUserId() {
		return updateUserId;
	}

	protected void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	protected String getUpdateUserName() {
		return updateUserName;
	}

	protected void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	protected LocalDateTime getUpdateTime() {
		return updateTime;
	}

	protected void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

}
