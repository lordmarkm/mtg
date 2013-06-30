package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/frontpage")
public interface AdminFrontpageController {

	@RequestMapping(value = "/newpost", method = RequestMethod.GET)
	ModelAndView newpost(Principal principal);
	
}
