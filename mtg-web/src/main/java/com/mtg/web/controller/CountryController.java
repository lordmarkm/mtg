package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/countries", "/cy"})
public interface CountryController {

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView browse(Principal principal);
	
	@RequestMapping("/{urlFragment}")
	ModelAndView country(Principal principal, String urlFragment);
	
}
