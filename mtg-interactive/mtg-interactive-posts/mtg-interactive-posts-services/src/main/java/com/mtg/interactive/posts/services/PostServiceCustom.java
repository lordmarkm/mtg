package com.mtg.interactive.posts.services;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.magic.MagicPlayer;

public interface PostServiceCustom {

	Post post(Post post, PostParentType parentType, long parentId,
			MagicPlayer author);
	
}
