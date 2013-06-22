package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.JSON;
import com.mtg.web.support.BundleOperation;

@Controller
@RequestMapping("/account/wantlist")
public interface AccountWantlistController {

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView load(Principal principal);
	
	@ResponseBody
	@RequestMapping("/add/{id}")
	JSON add(Principal principal, Long id);
	
	@ResponseBody
	@RequestMapping("/editnote/{id}")
	JSON editnote(Principal principal, Long id, String note);
	
	/**
	 * @param op - Don't be fooled by BundleOperation enum type, this operates on a Wanted
	 */
	@ResponseBody
	@RequestMapping("/{op}/{id}")
	JSON op(Principal principal, BundleOperation op, Long id);
	
}
