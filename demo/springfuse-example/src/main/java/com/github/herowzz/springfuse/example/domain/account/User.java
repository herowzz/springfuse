package com.github.herowzz.springfuse.example.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.github.herowzz.springfuse.data.domain.BaseUser;
import com.github.herowzz.springfuse.data.domain.annotation.Comment;

@Entity
@DynamicInsert
@DynamicUpdate
@Comment("用户")
public class User extends BaseUser {

	private static final long serialVersionUID = 7541116354582183048L;

	@Column(length = 20)
	@Comment("电话")
	private String phone;

	@Column(length = 50)
	@Comment("邮箱")
	private String email;

	@Column(length = 50)
	@Comment("昵称")
	private String nickname;

	@Column(length = 200)
	@Comment("头像")
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
