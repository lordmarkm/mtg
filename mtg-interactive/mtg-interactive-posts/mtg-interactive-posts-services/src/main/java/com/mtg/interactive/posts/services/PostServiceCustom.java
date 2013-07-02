package com.mtg.interactive.posts.services;

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
	 */
	public void hide(MagicPlayer player, Post post);

	/**
	 * Edit text only, titles can't be edited - mark last edit time
	 */
	public void edit(MagicPlayer player, Post post);
	
}