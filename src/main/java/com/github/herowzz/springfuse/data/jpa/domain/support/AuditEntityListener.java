package com.github.herowzz.springfuse.data.jpa.domain.support;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Configurable;

import com.github.herowzz.springfuse.data.jpa.domain.BaseEntity;

@Configurable
public class AuditEntityListener {

	@PrePersist
	public void touchForCreate(BaseEntity entity) {
		if (entity != null) {
			LocalDateTime now = LocalDateTime.now();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
		}
	}

	@PreUpdate
	public void touchForUpdate(BaseEntity entity) {
		if (entity != null) {
			entity.setUpdateTime(LocalDateTime.now());
		}
	}
}