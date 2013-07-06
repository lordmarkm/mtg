package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/u")
public interface ProfileCommentableController {

	@RequestMapping(value = "/{username}/comments/{page}/{size}")
	ModelAndView comments(Principal principal, String username, int page, int size);

	@RequestMapping(value = "/{username}/posts/{page}/{size}")
	ModelAndView posts(Principal principal, String username, int page, int size);

	/**
	 * Only admins or self can view player saved
	 * @return
	 */
	@RequestMapping(value = "/{username}/saved/{page}/{size}")
	ModelAndView saved(Principal principal, String username, int page, int size);

}
