package com.mtg.interactive.posts.services;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.interactive.Post;

public interface CommentServiceCustom {

	/**
	 * Comment on a post
	 */
	Comment onPost(Principal principal, Long postId, String text);

	/**
	 * Reply to another comment
	 */
	Comment onComment(Principal principal, Long commentId, String text);
	
	/**
	 * Find the fist non-Comment ancestor
	 */
	Post getProgenitor(Comment comment);
	
	/**
	 * "Delete" a comment. Only available to the author or a moderator
	 * @throws AccessDeniedException 
	 */
	void hide(Principal principal, Long id) throws AccessDeniedException;

}
