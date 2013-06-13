package com.mtg.web.controller.impl;

import java.security.Principal;

import org.springframework.web.servlet.ModelAndView;

public abstract class GenericController {

	protected String name(Principal principal) {
		return null == principal ? "Anonymous" : principal.getName();
	}

	protected ModelAndView mav(String viewname) {
		return new ModelAndView(viewname);
	}
	
}
