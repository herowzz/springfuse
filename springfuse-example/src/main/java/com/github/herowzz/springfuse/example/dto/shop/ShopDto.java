package com.github.herowzz.springfuse.example.dto.shop;

import com.github.herowzz.springfuse.example.domain.Shop;

public class ShopDto {

	public String id;

	public String name;

	public String address;

	public static ShopDto copy(Shop shop) {
		ShopDto dto = new ShopDto();
		dto.id = shop.getId();
		dto.name = shop.getName();
		dto.address = shop.getAddress();
		return dto;
	}

	@Override
	public String toString() {
		return "ShopDto [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
