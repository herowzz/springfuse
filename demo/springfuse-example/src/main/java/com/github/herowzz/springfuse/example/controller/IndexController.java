package com.github.herowzz.springfuse.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class IndexController {

	@ResponseBody
	@RequestMapping(value = "/")
	public String main() {
		return "";
	}

	@RequestMapping(value = "/csrf")
	@ResponseBody
	public String csrf() {
		return "{}";
	}

}
