package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUser;

@Entity
@DynamicInsert
@DynamicUpdate
public class User extends BaseUser {

	private static final long serialVersionUID = 7541116354582183048L;

	@Column(length = 20)
	private String phone;

	@Column(length = 50)
	private String email;

	@Column(length = 50)
	private String nickname;

	@Column(length = 200)
	private String avatar;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
