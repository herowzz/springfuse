package com.github.herowzz.springfuse.example.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.envers.ModifiedEntityNames;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.github.herowzz.springfuse.data.domain.support.RevisionEntityListener;

@Entity
@Table(name = "version_info")
@RevisionEntity(RevisionEntityListener.class)
@EntityListeners(AuditingEntityListener.class)
public class VersionEntity implements Serializable {

	private static final long serialVersionUID = 777326068558331770L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@RevisionNumber
	private long id;

	@RevisionTimestamp
	private Date versionTime;

	@LastModifiedBy
	private String updateUser;

	@ElementCollection
	@JoinTable(name = "version_changes", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "entityname")
	@ModifiedEntityNames
	private Set<String> relatedEntityNames = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getVersionTime() {
		return versionTime;
	}

	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Set<String> getRelatedEntityNames() {
		return relatedEntityNames;
	}

}
