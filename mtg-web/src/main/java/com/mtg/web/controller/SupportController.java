package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Help, FAQ, Login, etc
 * @author Mark
 */
@Controller
@RequestMapping("/support")
public interface SupportController {

    @RequestMapping("/welcome")
    ModelAndView welcome();
    
    @RequestMapping("/frontpage")
	ModelAndView frontpage();
    
	@RequestMapping("/faq")
	ModelAndView faq();
	
	@RequestMapping("/upcoming")
	ModelAndView upcoming();
	
	@RequestMapping("/updates")
	ModelAndView updates();

}
