package com.mtg.web.controller.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.controller.BrowseController;
import com.mtg.web.controller.GenericController;

@Component
public class BrowseControllerImpl extends GenericController implements BrowseController {

	private static Logger log = LoggerFactory.getLogger(BrowseControllerImpl.class);
	
	@Override
	public ModelAndView browse(Principal principal, @RequestParam(required=false) String uri) {
		
		log.info("Home page requested. requestor={}, target={}", name(principal), uri);
		
		return mav("index")
				.addObject("target", uri);
	}

}
