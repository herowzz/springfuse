package com.github.herowzz.springfuse.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.github.herowzz.springfuse.core.bean.enumtype.WhetherEnum;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;
import com.github.herowzz.springfuse.security.manager.UserSessionManager;

/**
 * 实体类基类,提供常用基础参数
 * @author wangzz
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 逻辑删除查询条件
	 */
	public static final String SOFT_DELETED_CLAUSE = "is_deleted = 0";

	/**
	 * 创建时间
	 */
	@Comment("创建时间")
	@Column(updatable = false, nullable = false)
	protected LocalDateTime createTime;

	/**
	 * 创建人ID
	 */
	@Comment("创建人ID")
	@Column(name = "create_user_id", updatable = false)
	protected String createUserId;

	/**
	 * 创建人姓名
	 */
	@Comment("创建人姓名")
	@Column(name = "create_user_name", updatable = false)
	protected String createUserName;

	/**
	 * 修改人ID
	 */
	@Comment("修改人ID")
	@Column(name = "update_user_id")
	protected String updateUserId;

	/**
	 * 修改人姓名
	 */
	@Comment("修改人姓名")
	@Column(name = "update_user_name")
	protected String updateUserName;

	/**
	 * 修改时间
	 */
	@Comment("修改时间")
	protected LocalDateTime updateTime;

	/**
	 * 是否删除<br>
	 * 0:未删除,1:已删除
	 */
	@Comment("是否删除")
	@Column(name = "is_deleted")
	protected WhetherEnum isDeleted = WhetherEnum.No;

	@PrePersist
	public void onCreate() {
		this.createTime = LocalDateTime.now();
		BaseUser user = UserSessionManager.getUser();
		if (user != null) {
			this.createUserId = user.getId();
			this.createUserName = user.getRealname();
		}
	}

	@PreUpdate
	public void onUpdate() {
		this.updateTime = LocalDateTime.now();
		BaseUser user = UserSessionManager.getUser();
		if (user != null) {
			this.updateUserId = user.getId();
			this.updateUserName = user.getRealname();
		}
	}

	public void onRemove() {
		this.isDeleted = WhetherEnum.Yes;
		this.onUpdate();
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public WhetherEnum getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(WhetherEnum isDeleted) {
		this.isDeleted = isDeleted;
	}

}
