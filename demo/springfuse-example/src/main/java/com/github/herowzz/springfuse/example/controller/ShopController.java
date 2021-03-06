package com.github.herowzz.springfuse.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.herowzz.springfuse.api.dto.ApiResult;
import com.github.herowzz.springfuse.api.dto.param.IdParam;
import com.github.herowzz.springfuse.example.domain.Shop;
import com.github.herowzz.springfuse.example.dto.shop.ShopDto;
import com.github.herowzz.springfuse.example.dto.shop.param.AddShopParam;
import com.github.herowzz.springfuse.example.dto.shop.param.UpdateShopParam;
import com.github.herowzz.springfuse.example.service.ShopService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/shop")
@Api(tags = "商店管理")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@PostMapping(value = "/list")
	public ApiResult<List<ShopDto>> list() {
		List<Shop> shopList = shopService.findAll();
		List<ShopDto> dtoList = shopList.stream().map(e -> ShopDto.copy(e)).collect(Collectors.toList());
		return ApiResult.build(dtoList);
	}

	@PostMapping(value = "/add")
	public ApiResult<?> add(@RequestBody @Valid AddShopParam param) {
		Shop Shop = param.copy();
		shopService.save(Shop);
		return ApiResult.ok();
	}

	@PostMapping(value = "/{id}")
	public ApiResult<ShopDto> show(@PathVariable("id") String id) {
		ApiResult<ShopDto> result = new ApiResult<>();
		Optional<Shop> ShopOptional = shopService.findById(id);
		if (!ShopOptional.isPresent()) {
			return result.objectNotFoundResult();
		}
		return ApiResult.build(ShopDto.copy(ShopOptional.get()));
	}

	@PostMapping(value = "/update")
	public ApiResult<?> update(@RequestBody @Valid UpdateShopParam param) {
		Optional<Shop> ShopOptional = shopService.findById(param.id);
		if (!ShopOptional.isPresent()) {
			return ApiResult.objectNotFound();
		}
		shopService.save(param.copy(ShopOptional.get()));
		return ApiResult.ok();
	}

	@PostMapping(value = "/delete")
	public ApiResult<?> delete(@RequestBody @Valid IdParam param) {
		shopService.delete(param.id);
		return ApiResult.ok();
	}

}
