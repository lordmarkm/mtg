package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Help, FAQ, Login, etc
 * @author Mark
 */
@Controller
@RequestMapping("/support/")
public interface SupportController {

	@RequestMapping("/faq")
	public ModelAndView faq();
	
}
