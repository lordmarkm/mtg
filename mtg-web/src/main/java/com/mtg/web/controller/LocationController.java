package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/community")
public interface LocationController {

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	ModelAndView communities(Principal principal);
	
}
