package com.github.herowzz.springfuse.example.dto.shop.param;

import com.github.herowzz.springfuse.example.domain.Shop;

public class AddShopParam {

	public String name;

	public String address;

	public Shop copy() {
		Shop shop = new Shop();
		shop.setName(this.name);
		shop.setAddress(this.address);
		return shop;
	}

}
