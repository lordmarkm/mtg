package com.mtg.web.controller;

import java.security.Principal;

import org.apache.commons.lang.Validate;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

public abstract class GenericController {

	protected String name(Principal principal) {
		return null == principal ? "Anonymous" : principal.getName();
	}

	protected ModelAndView mav() {
		return new ModelAndView();
	}
	
	protected ModelAndView mav(String viewname) {
		return new ModelAndView(viewname);
	}
	
	protected String firstError(BindingResult result) {
		Validate.isTrue(result.hasErrors());
        ObjectError error = result.getAllErrors().iterator().next();
        return error.getDefaultMessage();
	}
	
	protected String basic(String src) {
		src = src.replaceAll("\n", "br2nl");
		src = Jsoup.clean(src, Whitelist.basic());
		return src.replaceAll("br2nl", "\n"); 
	}
	
}
