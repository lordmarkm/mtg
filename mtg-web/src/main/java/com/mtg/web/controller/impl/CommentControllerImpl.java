package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.mtg.interactive.posts.services.CommentService;
import com.mtg.web.controller.CommentController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.JSON;

public class CommentControllerImpl extends GenericController implements CommentController {

	private static Logger log = LoggerFactory.getLogger(CommentControllerImpl.class);

	@Resource
	private CommentService comments;
	
	@Override
	public JSON onPost(Principal principal, @PathVariable Long id, @RequestParam String comment) {
		
		log.info("Comment on post. user={}, post={}, text={}", name(principal), id, comment);
		
		comments.onPost(principal, id, comment);
		
		return JSON.ok();
	}

	@Override
	public JSON onComment(Principal principal, @PathVariable Long id, @RequestParam String text) {
		
		log.info("Comment reply. user={}, comment={}, text={}", name(principal), id, text);

		comments.onComment(principal, id, text);
		
		return JSON.ok();
		
	}

}
