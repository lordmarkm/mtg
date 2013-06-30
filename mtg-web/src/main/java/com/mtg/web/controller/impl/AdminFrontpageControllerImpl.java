package com.mtg.web.controller.impl;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.controller.AdminFrontpageController;
import com.mtg.web.controller.GenericController;

@Component
public class AdminFrontpageControllerImpl extends GenericController implements AdminFrontpageController {

	private static Logger log = LoggerFactory.getLogger(AdminFrontpageControllerImpl.class);
	
	@Override
	public ModelAndView newpost(Principal principal) {
		log.info("New post form requested. admin = {}", name(principal));
		return mav("admin/frontpage-newpost");
	}

}
