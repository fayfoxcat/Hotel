package org.fox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/fox",method = RequestMethod.GET)
public class HtmlController {
	@RequestMapping(value="/acceptlist",method = RequestMethod.GET)
	public String shopList() {
		return "/acceptlist";
	}

	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "/login";
	}

	@RequestMapping(value="/order",method = RequestMethod.GET)
	public String Order() {
		return "/order";
	}
}
