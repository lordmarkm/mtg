package com.mtg.web.controller;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/comment")
public interface CommentController {

	@ResponseBody
	@RequestMapping(value = "/post/post/{id}", method = RequestMethod.POST)
	JSON onPost(Principal principal, Long id, String text);
	
	@ResponseBody
	@RequestMapping(value = "/post/comment/{id}", method = RequestMethod.POST)
	JSON onComment(Principal principal, Long id, String text);
	
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ModelAndView singleComment(Principal principal, Long id);
	
    @RequestMapping(value = "/permalink/{id}", method = RequestMethod.GET)
    ModelAndView permalink(Principal principal, Long id);
    
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    JSON delete(Principal principal, Long id) throws AccessDeniedException;
    
}
