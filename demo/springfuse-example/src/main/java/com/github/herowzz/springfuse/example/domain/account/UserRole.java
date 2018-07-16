package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUidEntity;

@Entity(name = "user_role")
@DynamicInsert
@DynamicUpdate
public class UserRole extends BaseUidEntity {

	private static final long serialVersionUID = 983308626699223127L;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private User user;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private Role role;

	public UserRole() {
	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
