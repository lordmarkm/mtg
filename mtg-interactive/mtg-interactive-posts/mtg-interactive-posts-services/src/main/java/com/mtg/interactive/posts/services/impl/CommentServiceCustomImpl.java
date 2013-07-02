package com.mtg.interactive.posts.services.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
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
	
	@Override
	public Comment onComment(Principal principal, Long commentId, String text) {
		Account account = accounts.findByUsername(principal.getName());
		Comment parent = comments.findOne(commentId);
		
		Validate.notNull(account);
		Validate.notNull(parent);
		
		MagicPlayer author = account.getPlayer();
		
		Comment comment = new Comment();
		comment.setComment(parent);
		comment.setText(text);
		comment.setAuthor(author);
		Comment saved = comments.save(comment);
		
		author.getComments().add(comment);
		
		parent.getReplies().add(comment);
		parent.setReplyCount(parent.getReplyCount() + 1);
		
		return saved;
	}
	
	@Override
	public Comment onPost(Principal principal, Long postId, String text) {
		Account account = accounts.findByUsername(principal.getName());
		Post post = posts.findOne(postId);
		
		Validate.notNull(account);
		Validate.notNull(post);

		MagicPlayer author = account.getPlayer();
		
		Comment comment = new Comment();
		comment.setPost(post);
		comment.setText(text);
		comment.setAuthor(author);
		Comment saved = comments.save(comment);

		author.getComments().add(saved);
		
		post.getReplies().add(saved);
		post.setReplyCount(post.getReplyCount() + 1);
		
		return saved;
	}

}
