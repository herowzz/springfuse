package com.github.herowzz.springfuse.example.dto.shop.param;

import javax.validation.constraints.NotBlank;

import com.github.herowzz.springfuse.example.domain.Shop;

public class UpdateShopParam {

	@NotBlank
	public String id;

	public String name;

	public String address;

	public Shop copy(Shop shop) {
		shop.setName(this.name);
		shop.setAddress(this.address);
		return shop;
	}

	@Override
	public String toString() {
		return "UpdateBookParam [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
