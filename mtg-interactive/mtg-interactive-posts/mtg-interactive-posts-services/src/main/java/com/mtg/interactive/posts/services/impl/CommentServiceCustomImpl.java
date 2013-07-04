package com.mtg.interactive.posts.services.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.interactive.posts.services.CommentServiceCustom;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@Transactional
public class CommentServiceCustomImpl implements CommentServiceCustom {

	@Resource
	private AccountService accounts;
	
	@Resource
	private PostService posts;
	
	@Resource
	private CommentService comments;
	
	protected Comment assembleComment(Principal principal, String text) {
	    Account account = accounts.findByUsername(principal.getName());
	    Validate.notNull(account);
	    
        Comment unsaved = new Comment();
        unsaved.setText(text);
        unsaved.setPostdate(DateTime.now());
        Comment comment = comments.save(unsaved);
        
        MagicPlayer author = account.getPlayer();
        author.getComments().add(comment);
        comment.setAuthor(author);
        
        return comment;
	}
	
	@Override
	public Comment onComment(Principal principal, Long commentId, String text) {
		Comment parent = comments.findOne(commentId);
		Validate.notNull(parent);
		
		Comment comment = assembleComment(principal, text);
		comment.setComment(parent);

		parent.getReplies().add(comment);
		parent.setReplyCount(parent.getReplyCount() + 1);
		
		return comment;
	}
	
	@Override
	public Comment onPost(Principal principal, Long postId, String text) {
		Post post = posts.findOne(postId);
		
		Validate.notNull(post);

		Comment comment = assembleComment(principal, text);
		comment.setPost(post);

		post.getReplies().add(comment);
		post.setReplyCount(post.getReplyCount() + 1);
		
		return comment;
	}

}
