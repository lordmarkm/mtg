package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/account/post")
public interface AccountPostController {

	/**
	 * Save a post
	 */
	@ResponseBody
	@RequestMapping("/save/{id}")
	JSON save(Principal principal, Long id);
	
	/**
	 * Unsave a post
	 */
	@ResponseBody
	@RequestMapping("/unsave/{id}")
	JSON unsave(Principal principal, Long id);
	
}
