package com.mtg.web.controller.impl;

import java.security.Principal;

import org.apache.commons.lang.Validate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

public abstract class GenericController {

	protected String name(Principal principal) {
		return null == principal ? "Anonymous" : principal.getName();
	}

	protected ModelAndView mav(String viewname) {
		return new ModelAndView(viewname);
	}
	
	protected String firstError(BindingResult result) {
		Validate.isTrue(result.hasErrors());
        ObjectError error = result.getAllErrors().iterator().next();
        return error.getDefaultMessage();
	}
	
}
