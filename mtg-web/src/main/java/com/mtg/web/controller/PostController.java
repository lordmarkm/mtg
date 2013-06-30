package com.mtg.web.controller;

import java.security.Principal;

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

	@ResponseBody
	@RequestMapping(value = "/{parentType}", method = RequestMethod.POST)
	JSON post(Principal principal, PostParentType parentType, PostForm post, BindingResult result);

	@RequestMapping(value = "/{parentType}/{parentId}")
	ModelAndView posts(Principal principal, PostParentType parentType,
			Long parentId, PageRequestDto request);
	
}
