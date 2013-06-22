package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u")
public interface ProfileController {

	@RequestMapping(method = RequestMethod.GET)
	ModelAndView ownprofile(Principal principal);
	
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	ModelAndView profile(Principal principal, String username);

	@RequestMapping(value = "/{username}/{bindername}")
	ModelAndView binder(Principal principal, String username, String bindername);
	
}
