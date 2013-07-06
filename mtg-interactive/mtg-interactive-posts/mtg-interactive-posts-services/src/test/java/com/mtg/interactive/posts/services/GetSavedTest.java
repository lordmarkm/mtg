package com.mtg.interactive.posts.services;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent;
import com.mtg.commons.models.interactive.PostParent.PostParentType;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.interactive.posts.services.config.PostsServicesConfig;
import com.mtg.security.config.SecurityConfig;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonsPersistenceConfig.class, CommonsServicesConfig.class,
		SecurityConfig.class, PostsServicesConfig.class, PostsServicesTestConfig.class})
@Transactional
public class GetSavedTest {

	@Resource
	private PostService posts;

	@Resource
	private PlayerService players;
	
	@Resource
	private AccountService accounts;
	
	@Test
	public void config() {
		
	}
	
	protected Account accountWithPlayer() {
		MagicPlayer player = new MagicPlayer();
		player.setName("markm");
		player.setDescription("just some guy");
		
		Account account = new Account();
		account.setUsername("markm");
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_USER");
		return accounts.save(account);
	}
	
	protected Post post(Account author) {
		PostParent parent = new PostParent();
		parent.setParentType(PostParentType.frontpage);
		
		Post post = new Post();
		post.setTitle("Hello");
		post.setText("world");
		post.setPostdate(DateTime.now());
		post.setAuthor(author.getPlayer());
		post.setParent(parent);
		return posts.save(post);
	}
	
	@Test
	public void test() {
		Account account = accountWithPlayer();
		Post post = post(account);
		
		account.getPlayer().getSaved().add(post);
		posts.findSaved(account.getPlayer().getName(), new PageRequest(0,10));
	}
}
