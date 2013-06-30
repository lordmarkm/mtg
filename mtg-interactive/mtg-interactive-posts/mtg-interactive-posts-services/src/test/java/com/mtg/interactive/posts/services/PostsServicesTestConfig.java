package com.mtg.interactive.posts.services;

import static org.mockito.Mockito.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.commons.services.PlayerService;
import com.mtg.security.services.AccountService;

@Configuration
public class PostsServicesTestConfig {

	@Bean
	public PlayerService playerService() {
		return mock(PlayerService.class);
	}
	
	@Bean
	public AccountService accountService() {
		return mock(AccountService.class);
	}
	
	@Bean
	public MeetupService meetupService() {
		return mock(MeetupService.class);
	}
	
	@Bean
	public CityService cityService() {
		return mock(CityService.class);
	}
	
	@Bean
	public CountryService countryService() {
		return mock(CountryService.class);
	}
	
}
