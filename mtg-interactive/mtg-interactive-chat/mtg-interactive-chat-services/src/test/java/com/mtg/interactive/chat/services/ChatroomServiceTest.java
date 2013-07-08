package com.mtg.interactive.chat.services;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.commons.services.PlayerService;
import com.mtg.commons.services.config.CommonsPersistenceConfig;
import com.mtg.commons.services.config.CommonsServicesConfig;
import com.mtg.interactive.chat.config.ChatServicesConfig;
import com.mtg.interactive.chat.models.Chatroom;
import com.mtg.interactive.chat.models.Chatroom.Type;
import com.mtg.security.config.SecurityConfig;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ChatServicesConfig.class,
		CommonsPersistenceConfig.class, CommonsServicesConfig.class, SecurityConfig.class, ChatServicesTestConfig.class})
@Transactional
public class ChatroomServiceTest {

	@Resource
	private PlayerService players;
	
	@Resource
	private AccountService accounts;
	
	@Resource
	private ChatroomService chatrooms;
	
	public Account strangerWithPlayer() {
		MagicPlayer player = new MagicPlayer();
		player.setName("markm2");
		player.setDescription("just some guy");
		
		Account account = new Account();
		account.setUsername("markm2");
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_USER");
		return accounts.save(account);
	}
	
	public Account accountWithPlayer() {
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
	
	public Chatroom chatroom() {
		Chatroom c = new Chatroom();
		return chatrooms.save(c);
	}
	
	@Test
	public void testFind() {
		Account a1 = strangerWithPlayer();
		Account a2 = accountWithPlayer();
		
		MagicPlayer m1 = a1.getPlayer();
		MagicPlayer m2 = a2.getPlayer();
		
		Chatroom room = chatroom();
		room.setType(Type.personal);
		List<MagicPlayer> chatters = room.getChatters();
		chatters.add(m1);
		chatters.add(m2);
		
		Chatroom retrieved = chatrooms.findPersonal(m1, m2);
		System.out.println(retrieved);
		
		Chatroom ret = chatrooms.findPersonal(m2, m1);
		System.out.println(ret);
	}
}
