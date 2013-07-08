package com.mtg.interactive.chat.services;

import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mtg.audit.service.AuditLogger;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.CountryService;
import com.mtg.commons.services.MeetupService;
import com.mtg.mail.service.MailSenderService;

@Configuration
public class ChatServicesTestConfig {

	@Bean
	public MailSenderService mailer() {
		return mock(MailSenderService.class);
	}
	
	@Bean
	public AuditLogger audit() {
		return mock(AuditLogger.class);
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
