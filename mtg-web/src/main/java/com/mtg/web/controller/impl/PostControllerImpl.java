package com.mtg.web.controller.impl;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.services.AccountService;
import com.mtg.web.controller.GenericController;
import com.mtg.web.controller.PostController;
import com.mtg.web.dto.DtoMaker;
import com.mtg.web.dto.JSON;
import com.mtg.web.dto.PageRequestDto;
import com.mtg.web.dto.PostForm;

@Component
public class PostControllerImpl extends GenericController implements PostController {

	private static Logger log = LoggerFactory.getLogger(PostControllerImpl.class);

	@Resource
	private PostService posts;
	
	@Resource
	private AccountService accounts;
	
	private MagicPlayer player(Principal principal) {
		return accounts.findByUsername(principal.getName()).getPlayer();
	}
	
	@Override
	public JSON post(Principal principal, @PathVariable PostParentType parentType,
			@Valid PostForm form, BindingResult result) {
		
		log.info("Post request. user = {}, parentType = {}, post = {}", name(principal), parentType, form);
		
        if(result.hasErrors()) {
            return JSON.error(result.getAllErrors().iterator().next().getDefaultMessage());
        }
		
        MagicPlayer author = player(principal);
        Post post = form.toPost();
        post.setAuthor(author);
        
        Post saved = posts.post(post, parentType, form.getParentId(), author);
        
		return JSON.ok().put("post", DtoMaker.transform(saved));
	}

	@Override
	public ModelAndView posts(Principal principal, @PathVariable PostParentType parentType,
			@PathVariable Long parentId, PageRequestDto request) {
		
		log.info("Posts requested. user={}, parent= {} {}, page = {}", name(principal), parentType, parentId, request);
		
		List<Post> postsPage =  posts.findByParent(parentType, parentId, request.toPageRequest());
		return mav("support/frontpage-posts").addObject("posts", postsPage);
	}

}
