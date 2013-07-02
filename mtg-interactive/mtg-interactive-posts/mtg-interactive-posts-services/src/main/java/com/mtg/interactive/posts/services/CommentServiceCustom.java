package com.mtg.interactive.posts.services;

import java.security.Principal;

import com.mtg.commons.models.interactive.Comment;

public interface CommentServiceCustom {

	/**
	 * Comment on a post
	 */
	Comment onPost(Principal principal, Long postId, String text);

	/**
	 * Reply to another comment
	 */
	Comment onComment(Principal principal, Long commentId, String text);
	
}
