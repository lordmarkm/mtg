package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/search")
public interface SearchController {

	@ResponseBody
	@RequestMapping("/navbar")
	JSON navbar(String query);
	
}
