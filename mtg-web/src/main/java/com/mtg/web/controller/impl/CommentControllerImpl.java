package com.mtg.web.controller.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.web.controller.CommentController;
import com.mtg.web.controller.GenericController;
import com.mtg.web.dto.DtoMaker;
import com.mtg.web.dto.JSON;

@Component
public class CommentControllerImpl extends GenericController implements CommentController {

	private static Logger log = LoggerFactory.getLogger(CommentControllerImpl.class);

	@Resource
	private CommentService comments;
	
	@Override
	public JSON onPost(Principal principal, @PathVariable Long id, @RequestParam String text) {
		
		log.info("Comment on post. user={}, post={}, text={}", name(principal), id, text);
		
		Comment comment = comments.onPost(principal, id, basic(text));
		
		return JSON.ok().put("comment", DtoMaker.transform(comment));
	}

	@Override
	public JSON onComment(Principal principal, @PathVariable Long id, @RequestParam String text) {
		
		log.info("Comment reply. user={}, comment={}, text={}", name(principal), id, text);

		Comment comment = comments.onComment(principal, id, basic(text));
		
		return JSON.ok().put("comment", DtoMaker.transform(comment));
	}

    @Override
    public ModelAndView singleComment(Principal principal, @PathVariable Long id) {
        log.info("Request to view a single comment. user={}, comment={}", name(principal), id);
        
        Comment comment = comments.findOne(id);
        Validate.notNull(comment);
        
        return mav("post/comment")
                .addObject(Comment.PREFERRED_MODEL_KEY, comment);
    }

    
    @Override
    public ModelAndView permalink(Principal principal, @PathVariable Long id) {
        log.info("Request to view a single comment. user={}, comment={}", name(principal), id);
        
        Comment comment = comments.findOne(id);
        Validate.notNull(comment);
        
        Post post = comments.getProgenitor(comment);
        Validate.notNull(post);
        
        return mav("post/comment-context")
        		.addObject(Post.PREFERRED_MODEL_KEY, post)
                .addObject(Comment.PREFERRED_MODEL_KEY, comment);
    }

	@Override
	public JSON delete(Principal principal, @PathVariable Long id) throws AccessDeniedException {
		log.info("Request to delete a comment. user={}, comment={}", name(principal), id);
		comments.hide(principal, id);
		return JSON.ok();
	}
}
