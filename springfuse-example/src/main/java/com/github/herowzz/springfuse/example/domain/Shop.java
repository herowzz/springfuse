package com.github.herowzz.springfuse.example.domain;

import javax.persistence.Entity;

import com.github.herowzz.springfuse.jpa.domain.BaseUidEntity;

@Entity
public class Shop extends BaseUidEntity {

	private static final long serialVersionUID = -1423036044397574897L;

	private String name;

	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
