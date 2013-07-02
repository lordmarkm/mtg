package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/cities","/ct"})
public interface CityController {

	String[] PATTERNS = new String[]{"/cities/**", "/ct/**"};
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView browse(Principal principal);
	
	@RequestMapping(value = "/{urlFragment}", method = RequestMethod.GET)
	ModelAndView city(Principal principal, String urlFragment);
	
	@RequestMapping(value = "/{urlFragment}/players", method = RequestMethod.GET)
	ModelAndView players(Principal principal, String urlFragment);

	@RequestMapping(value = "/{urlFragment}/newpost", method = RequestMethod.GET)
	ModelAndView newpost(Principal principal, String urlFragment);
	
	@RequestMapping(value = "/{urlFragment}/manage", method = RequestMethod.GET)
	ModelAndView manage(Principal principal, String urlFragment);
	
}
