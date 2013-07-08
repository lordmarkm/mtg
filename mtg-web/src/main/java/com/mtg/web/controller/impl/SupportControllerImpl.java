package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.interactive.posts.services.FrontpageService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.SupportController;

@Component
public class SupportControllerImpl extends GenericController implements SupportController {

	private static Logger log = LoggerFactory.getLogger(SupportControllerImpl.class);
	
	@Resource
	private FrontpageService frontpage;
	
    @Override
    public ModelAndView welcome() {
        return mav("support/welcome");
    }
    
    @Override
    public ModelAndView frontpage() {
    	return mav("support/frontpage");
    }
    
	@Override
	public ModelAndView faq() {
		return mav("support/faq");
	}

	@Override
	public ModelAndView upcoming() {
		return mav("support/upcoming");
	}

	@Override
	public ModelAndView updates() {
		return mav("support/updates");
	}

	@Override
	public ModelAndView etiquette(Principal principal) {
		log.info("Ettiquette page requested. user = {}", name(principal));
		return mav("support/etiquette");
	}

	@Override
	public ModelAndView formatting(Principal principal) {
		log.info("Formatting help page requested. user = {}", name(principal));
		return mav("support/formatting");
	}
}
