package com.mtg.security.services.support;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;

@Service
public class MtgUserDetailsService implements UserDetailsService {

	private static Logger log = LoggerFactory.getLogger(MtgUserDetailsService.class);
	
	@Resource
	private AccountService accounts;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Checking username [{}]", username);
		
		Account account = accounts.findByUsername(username);
		if(null == account) {
			throw new UsernameNotFoundException("User " + username + " not found.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		String[] authStrings = account.getAuthorities().split(", ");
		for(String authString : authStrings) {
			authorities.add(new SimpleGrantedAuthority(authString));
		}
		
		UserDetails ud = new User(account.getUsername(), account.getPassword(), authorities);
		log.info("Found: {}", ud);
		return ud;
	}

}
