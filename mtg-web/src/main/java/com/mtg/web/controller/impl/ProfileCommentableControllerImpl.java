package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.ProfileCommentableController;

@Component
public class ProfileCommentableControllerImpl extends GenericController implements ProfileCommentableController {

	private static Logger log = LoggerFactory.getLogger(ProfileCommentableControllerImpl.class);
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private CommentService comments;
	
	@Resource
	private PostService posts;
	
	private MagicPlayer player(Principal principal) {
		if(null != principal) {
			return accounts.findByUsername(principal.getName()).getPlayer();
		}
		return null;
	}

	@Override
	public ModelAndView comments(Principal principal, @PathVariable String username, 
			@PathVariable int page, @PathVariable int size) {

		log.info("Player profile comments requested. requestor = {}, player={}", name(principal), username);
		
		Account user = accounts.findByUsername(username);
		Validate.notNull(user);

		Page<Comment> userComments = comments.findByAuthor(user.getPlayer(), new PageRequest(page, size));

		return mav("profile/profile-comments")
				.addObject("user", user)
				.addObject("comments", userComments);
	}

	@Override
	public ModelAndView posts(Principal principal, @PathVariable String username, 
			@PathVariable int page, @PathVariable int size) {

		log.info("Player profile posts requested. requestor = {}, player={}", name(principal), username);

		Account user = accounts.findByUsername(username);
		Validate.notNull(user);	
		
		Page<Post> userPosts = posts.findByAuthor(user.getPlayer(), new PageRequest(page, size));
		
		return mav("profile/profile-posts")
				.addObject("user", user)
				.addObject("posts", userPosts);
	}

}
