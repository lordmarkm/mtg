package com.mtg.interactive.posts.services;

public interface PlayerPostService {

	/**
	 * When player wants to save a post for future user
	 * @param username
	 * @param id - post id
	 * @return true=success
	 */
	boolean save(String username, Long id);

	boolean unsave(String username, Long id);
	
}
