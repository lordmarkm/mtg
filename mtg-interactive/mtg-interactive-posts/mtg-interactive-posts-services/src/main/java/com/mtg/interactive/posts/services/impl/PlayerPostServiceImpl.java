package com.mtg.interactive.posts.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.posts.services.PlayerPostService;
import com.mtg.interactive.posts.services.PostService;
import com.mtg.security.services.AccountService;

@Service
@Transactional
public class PlayerPostServiceImpl implements PlayerPostService {

	@Resource
	private AccountService accounts;
	
	@Resource
	private PostService posts;
	
	private MagicPlayer player(String username) {
		return accounts.findByUsername(username).getPlayer();
	}
	
	@Override
	public boolean save(String username, Long id) {
		
		Post post = posts.findOne(id);
		Validate.notNull(post);
		
		MagicPlayer player = player(username);
		
		if(player.getSaved().contains(post)) {
			return false;
		}
		
		player.getSaved().add(0, post);
		return true;
	}

	@Override
	public boolean unsave(String username, Long id) {
		Post post = posts.findOne(id);
		Validate.notNull(post);
		
		MagicPlayer player = player(username);

		return player.getSaved().remove(post);
	}
	
	
}
