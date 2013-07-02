package com.mtg.interactive.posts.services;

import org.apache.commons.lang.RandomStringUtils;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.models.Account;

public class Util {

	public static Account account() {
		MagicPlayer player = new MagicPlayer();
		player.setName("markm");
		player.setDescription("just some guy");
		
		Account account = new Account();
		account.setUsername("markm");
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_ADMIN");
		return account;
	}
	
	public static Account account2() {
		String name = RandomStringUtils.randomAlphabetic(5);
		
		MagicPlayer player = new MagicPlayer();
		player.setName(name);
		player.setDescription("just some guy");
		
		Account account = new Account();
		account.setUsername(name);
		account.setPassword("123qwe");
		account.setPlayer(player);
		account.setAuthorities("ROLE_USER");
		return account;
	}
	
	public static City dgte() {
		City dgte = new City();
		dgte.setName("Dumaguete");
		dgte.setDescription("TCOGP");
		return dgte;
	}
	
}
