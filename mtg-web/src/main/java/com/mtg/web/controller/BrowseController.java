package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * This should be the only Controller that returns a non-ResponseBody ModelAndView
 * @author Mark
 */
@Controller
public interface BrowseController {

	/**
	 * Return the "main" page, whatever it is
	 * 6/20/2013 - I think it should be the "latest posts" page, which doesn't exist yet
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView browse(Principal principal, String uri);
	
}
