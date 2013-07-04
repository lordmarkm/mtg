package com.mtg.commons.models.interactive;

import java.util.List;

public interface Postable {

	String getUrlFragment();
	List<Post> getPosts();
	
}
