package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/countries", "/cy"})
public interface CountryController {

	String[] PATTERNS = new String[]{"/countries/**", "/cy/**"};
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView browse(Principal principal);
	
	@RequestMapping("/{urlFragment}")
	ModelAndView country(Principal principal, String urlFragment);
	
	@RequestMapping(value = "/{urlFragment}/players", method = RequestMethod.GET)
	ModelAndView players(Principal principal, String urlFragment);

	@RequestMapping(value = "/{urlFragment}/newpost", method = RequestMethod.GET)
	ModelAndView newpost(Principal principal, String urlFragment);
	
	@RequestMapping(value = "/{urlFragment}/manage", method = RequestMethod.GET)
	ModelAndView manage(Principal principal, String urlFragment);
	
}
