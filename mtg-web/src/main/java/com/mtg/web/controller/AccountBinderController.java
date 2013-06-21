package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.BinderForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/account")
public interface AccountBinderController {
	@RequestMapping(value = "/newbinder", method = RequestMethod.GET)
	ModelAndView newbinder(Principal principal);
	
	@ResponseBody
	@RequestMapping(value = "/newbinder", method = RequestMethod.POST)
	JSON newbinder(Principal principal, BinderForm form, BindingResult result);
	
	@RequestMapping(value = "/editbinder/{urlFragment}", method = RequestMethod.GET)
	ModelAndView editbinder(Principal principal, String urlFragment);
	
	@ResponseBody
	@RequestMapping(value = "/editbinder/page/{urlFragment}/{page}")
	ModelAndView editbinderPage(Principal principal, String urlFragment, Integer page);
	
	@ResponseBody
	@RequestMapping(value = "/editbinder/add/{urlFragment}/{page}/{cardId}", method = RequestMethod.POST)
	JSON addCard(Principal principal, String urlFragment, Integer page, Long cardId);
	
	@ResponseBody
	@RequestMapping(value = "/deletebinder/{urlFragment}")
	JSON deleteBinder(Principal principal, String urlFragment);
}
