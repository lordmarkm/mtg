package com.mtg.web.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.ImageForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/account")
public interface AccountController {

	@RequestMapping("/dashboard")
	ModelAndView dashboard(Principal principal);

	@RequestMapping(value = "/newbinder", method = RequestMethod.GET)
	ModelAndView newbinder(Principal principal);
	
	@ResponseBody
	@RequestMapping(value = "/newbinder", method = RequestMethod.POST)
	JSON newbinder(Principal principal, BinderForm form, BindingResult result);
	
	@ResponseBody
	@RequestMapping(value = "/upload/profilepic")
	JSON uploadProfilePic(Principal principal, ImageForm form) throws IOException;
	
}
