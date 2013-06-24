package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/c/")
public interface CityController {

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView browse(Principal principal);
	
	@RequestMapping(value = "/{urlFragment}", method = RequestMethod.GET)
	ModelAndView city(Principal principal, String urlFragment);
	
}
