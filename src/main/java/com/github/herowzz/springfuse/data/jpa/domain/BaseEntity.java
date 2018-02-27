package com.github.herowzz.springfuse.data.jpa.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.github.herowzz.springfuse.data.jpa.domain.support.AuditEntityListener;

/**
 * 实体类基类,提供常用基础参数
 * @author wangzz
 */
@MappedSuperclass
@Audited
@EntityListeners(AuditEntityListener.class)
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否删除<br>
	 * 0:未删除; 1:已删除
	 */
	@Column(name = "is_deleted")
	protected Boolean deleted = false;

	/**
	 * 创建时间
	 */
	@CreatedDate
	@Column(updatable = false)
	protected LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	@LastModifiedDate
	protected LocalDateTime updateTime;

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
