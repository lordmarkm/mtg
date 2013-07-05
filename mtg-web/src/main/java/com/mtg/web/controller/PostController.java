package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.PageRequestDto;
import com.mtg.web.dto.PostForm;

@Controller
@RequestMapping("/post")
public interface PostController {

    String[] PATTERNS = new String[] {"/post/**"};
    
	/**
	 * View individual post + comments
	 */
	@RequestMapping(value = "/{id}/{urlFragment}")
	ModelAndView viewpost(Principal principal, Long id, String urlFragment);
	
	/**
	 * New post
	 */
	@ResponseBody
	@RequestMapping(value = "/{parentType}", method = RequestMethod.POST)
	JSON post(Principal principal, PostParentType parentType, PostForm post, BindingResult result);

	/**
	 * Display post list
	 */
	@RequestMapping(value = "/{parentType}/{parentId}/{parentUrl}", method = RequestMethod.GET)
	ModelAndView posts(Principal principal, PostParentType parentType,
			Long parentId, String parentUrl, PageRequestDto request);
	
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	JSON delete(Principal principal, Long id) throws AccessDeniedException;
}
