package com.mtg.interactive.posts.services.impl;

import java.security.Principal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.locations.Location;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.CommentService;
import com.mtg.interactive.posts.services.CommentServiceCustom;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

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
		cascadeIncrementReplyCount(parent);
		
		return comment;
	}

	//when somebody comments on a comment, he is not only replying to direct parent, but to all ancestors
	protected void cascadeIncrementReplyCount(Comment comment) {
		comment.setReplyCount(comment.getReplyCount() + 1);
		if (null != comment.getPost()) {
			Post progenitor = comment.getPost();
			progenitor.setReplyCount(progenitor.getReplyCount() + 1);
		} else if(null != comment.getComment()) {
			cascadeIncrementReplyCount(comment.getComment());
		}
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

	@Override
	public Post getProgenitor(Comment comment) {
		
		Comment lastComment = comment;
		Post progenitor = null;
		while(null == progenitor) {
			progenitor = lastComment.getPost();
			lastComment = lastComment.getComment();
		
			//nothing found, should never happen, but catch just in case
			if(null == lastComment) break;
		}
		
		return progenitor;
	}

	@Override
	public void hide(Principal principal, Long id) throws AccessDeniedException {
		Account requestor = accounts.findByUsername(principal.getName());
		Comment comment = comments.findOne(id);
		Validate.notNull(comment);
		Validate.notNull(requestor);
		
		Location location = comment.getProgenitor().getParent().getLocationParent();
		boolean moderator = location != null && location.getModerators().contains(requestor.getPlayer());
		
		if(comment.getAuthor().equals(requestor.getPlayer())      //grant if author
				|| Roles.hasRole(requestor, Roles.ROLE_ADMIN)     //grant if admin
				|| moderator) {									  //grant if location moderator
			comment.setDeleted(true);
		} else {
			throw new AccessDeniedException("No! I am too sexy for you!");
		}
	}

}
