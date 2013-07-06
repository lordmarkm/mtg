package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.locations.Location;
import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/community")
public interface LocationController {

	@RequestMapping(value = "/browse", method = RequestMethod.GET)
	ModelAndView communities(Principal principal);

	/**
	 * @param type - country, city, meetup
	 * @param id - new mod id
	 */
	@ResponseBody
	@RequestMapping(value = "/makemod/{type}/{id}/{playerId}")
	JSON makemod(Principal principal, Location.Type type, Long id, Long playerId);
	
}
