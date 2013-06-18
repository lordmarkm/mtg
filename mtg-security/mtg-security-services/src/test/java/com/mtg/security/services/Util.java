package com.mtg.security.services;

import org.joda.time.DateTime;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;

public class Util {

	public static Account account() {
		AccountInfo aInfo = new AccountInfo();
		aInfo.setJoined(DateTime.now());
		aInfo.setLastLogin(DateTime.now());
		
		MagicPlayer player = new MagicPlayer();
		player.setName("mark");
		player.setDescription("mark desc");
		
		Account a = new Account();
		a.setUsername("mark");
		a.setPassword("123qwe");
		a.setAuthorities("ROLE_CLOWN");
		a.setPlayer(player);
		a.setInfo(aInfo);
		
		return a;
	}
	
	public static City city() {
		return new City("Dumaguete", "City of gentle ppl");
	}
}
