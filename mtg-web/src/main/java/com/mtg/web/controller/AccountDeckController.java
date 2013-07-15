package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.JSON;
import com.mtg.web.dto.NewDeckForm;

@Controller
@RequestMapping("/account/deck")
public interface AccountDeckController {

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	ModelAndView newdeckForm(Principal principal);
	
	@ResponseBody
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	JSON newdeck(Principal principal, NewDeckForm form, BindingResult binding);

	@RequestMapping("/edit/{id}/{urlFragment}")
	ModelAndView edit(Principal principal, Long id, String urlFragment);
	
}
