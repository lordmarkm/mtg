package com.mtg.interactive.posts.services;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.magic.MagicPlayer;

public interface PostServiceCustom {

	/**
	 * New post
	 */
	Post post(Post post, PostParentType parentType, long parentId,
			MagicPlayer author);

	/**
	 * "Delete"
	 * @throws AccessDeniedException 
	 */
	void hide(Principal principal, Long id) throws AccessDeniedException;

	/**
	 * Edit text only, titles can't be edited - mark last edit time
	 */
	public void edit(MagicPlayer player, Post post);
	
	/**
	 * Find frontpage posts for a player
	 */
	List<Post> findByFrontpageOrLocation(MagicPlayer player, Pageable pageRequest);

}
