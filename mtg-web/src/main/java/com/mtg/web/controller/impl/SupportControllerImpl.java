package com.mtg.web.controller.impl;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.SupportController;

@Component
public class SupportControllerImpl extends GenericController implements SupportController {

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

}
