package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/meetups", "/m"})
public interface MeetupController {

	String[] PATTERNS = new String[] {"/meetups/**", "/m/**"};
	
    @RequestMapping(value = "/{url}", method = RequestMethod.GET)
    ModelAndView browse(String url);

    @RequestMapping(value = "{urlFragment}/newpost", method = RequestMethod.GET)
	ModelAndView newpost(Principal principal, String urlFragment);
    
    @RequestMapping(value = "{urlFragment}/players", method = RequestMethod.GET)
    ModelAndView players(Principal principal, String urlFragment);
    
}
