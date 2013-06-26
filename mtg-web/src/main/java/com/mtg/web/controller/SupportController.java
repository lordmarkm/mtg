package com.mtg.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.JSON;

/**
 * Help, FAQ, Login, etc
 * @author Mark
 */
@Controller
@RequestMapping("/support")
public interface SupportController {

    @RequestMapping("/welcome")
    ModelAndView welcome();
    
	@RequestMapping("/faq")
	ModelAndView faq();
	
	@RequestMapping("/upcoming")
	ModelAndView upcoming();
	
	@RequestMapping("/updates")
	ModelAndView updates();

}
