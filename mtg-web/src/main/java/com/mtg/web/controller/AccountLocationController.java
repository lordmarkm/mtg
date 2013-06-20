package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.AddCityForm;
import com.mtg.web.dto.AddMeetupForm;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/account")
public interface AccountLocationController {
	
	@RequestMapping(value = "/addcity", method = RequestMethod.GET)
	ModelAndView addCity(Principal principal);

	@ResponseBody
	@RequestMapping(value = "/addcity", method = RequestMethod.POST)
	JSON addCity(Principal principal, AddCityForm form, BindingResult result);
	
	@ResponseBody
	@RequestMapping(value = "/removecity/{cityId}", method = RequestMethod.POST)
	JSON removeCity(Principal principal, Long cityId);
	
	@RequestMapping(value = "/addmeetup", method = RequestMethod.GET)
	ModelAndView addMeetup(Principal principal);

	@ResponseBody
	@RequestMapping(value = "/addmeetup", method = RequestMethod.POST)
	JSON addMeetup(Principal principal, AddMeetupForm form, BindingResult result);
	
	@ResponseBody
	@RequestMapping(value = "/removemeetup/{meetupId}", method = RequestMethod.POST)
	JSON removeMeetup(Principal principal, Long meetupId);
	
	@ResponseBody
	@RequestMapping(value = "/selectflag/{countryId}", method = RequestMethod.POST)
	JSON selectFlag(Principal principal, Long countryId);
	
}
