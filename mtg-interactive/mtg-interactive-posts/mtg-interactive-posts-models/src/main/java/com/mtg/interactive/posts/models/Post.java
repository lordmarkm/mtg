package com.mtg.interactive.posts.models;

import java.util.List;

import org.joda.time.DateTime;

import com.mtg.commons.models.magic.MagicPlayer;

public class Post implements HasPosts {

	private MagicPlayer author;
	
	private List<Post> posts;
	
	private int depth;
	
	private DateTime postdate;
	
	@Override
	public List<Post> getPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

}
