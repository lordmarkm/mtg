package com.mtg.interactive.posts.models;

import java.util.List;

public interface HasPosts {

	List<Post> getPosts();
	int getDepth();
	
}
