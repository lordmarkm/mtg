package com.mtg.commons.models.interactive;

import java.util.List;

public interface Commentable {

	public List<Comment> getReplies();
	public int getReplyCount();
	
}
