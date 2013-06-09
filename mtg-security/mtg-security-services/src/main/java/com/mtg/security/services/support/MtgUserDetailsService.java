package com.mtg.security.services.support;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mtg.security.models.Account;

@Component
public class MtgUserDetailsService implements UserDetailsService {

	@Resource
	private AccountService accounts;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
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
		return ud;
	}

}
