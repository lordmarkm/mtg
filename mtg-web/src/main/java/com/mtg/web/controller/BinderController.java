package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.locations.Location;

@Controller
@RequestMapping("/binders")
public interface BinderController {

	@RequestMapping(value = "/browse")
	ModelAndView browse(Principal principal);
	
	/**
	 * table.dataTables() call should be on the MAV this returns, to allow direct linking (not ajax-loaded from /browse)
	 * @param id - location id
	 */
	@RequestMapping(value = "/browse/{filterType}/{id}")
	ModelAndView browse(Principal principal, Location.Type filterType, Long id);
	
}
