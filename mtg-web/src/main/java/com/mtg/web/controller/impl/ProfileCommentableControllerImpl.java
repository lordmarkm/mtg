package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;
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
	
	private Account account(Principal principal) {
		return null == principal ? null : accounts.findByUsername(principal.getName());
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

		Account user = account(principal);
		Account target = accounts.findByUsername(username);
		Validate.notNull(target);	
		
		Page<Post> userPosts = posts.findByAuthor(target.getPlayer(), new PageRequest(page, size));
		
		return mav("profile/profile-posts")
				.addObject("user", user)
				.addObject("target", target)
				.addObject("posts", userPosts);
	}

	@Override
	public ModelAndView saved(Principal principal, @PathVariable String username,
			@PathVariable int page, @PathVariable int size) {

		log.info("Player saved posts requested. requestor = {}, player={}", name(principal), username);
		
		if(null == principal) {
			throw new AccessDeniedException("No! I am too sexy for you.");
		}
		
		Account requestor = accounts.findByUsername(principal.getName());
		if(requestor.getUsername().equals(username) || Roles.hasRole(requestor, Roles.ROLE_ADMIN)) {
			Account user = accounts.findByUsername(username);
			Page<Post> userSaved = posts.findSaved(username, new PageRequest(page, size));
			Validate.notNull(user);
			
			return mav("profile/profile-saved")
					.addObject("user", user)
					.addObject("admin", Roles.hasRole(user, Roles.ROLE_ADMIN))
					.addObject("posts", userSaved);
		} else {
			throw new AccessDeniedException("No! I am too sexy for you.");
		}
	}

}
