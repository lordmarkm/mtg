package com.mtg.admin.services.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.springframework.stereotype.Service;

import com.mtg.admin.services.AdminService;
import com.mtg.admin.services.support.BannableType;
import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Meetup;
import com.mtg.commons.service.support.OpResult;
import com.mtg.commons.services.CityService;
import com.mtg.commons.services.MeetupService;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.support.Roles;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AccountService accounts;
	
	@Resource
	private CityService cities;
	
	@Resource
	private MeetupService meetups;
	
	@Override
	public OpResult ban(BannableType type, Long id) {

		switch(type) {
		case player:
			return banUser(id);
		case city:
			return banCity(id);
		case meetup:
			return banMeetup(id);
		default:
			throw new IllegalArgumentException("Unsupported bannable type: " + type);
		}
	
	}
	
	protected OpResult banUser(Long id) {
		Account account = accounts.findOne(id);
		Validate.notNull(account);
		
		if(Roles.hasRole(account, Roles.ADMIN)) {
			return OpResult.error("Can't ban an admin.");
		}
		
		account.setBanned(true);
		accounts.save(account);
		
		return OpResult.ok(account.getUsername() + " has been banned.");
	}
	
	protected OpResult banCity(Long id) {
		City city = cities.findOne(id);
		Validate.notNull(city);
		
		city.setBanned(true);
		cities.save(city);
		
		return OpResult.ok(city.getName() + " has been banned.");
	}

	protected OpResult banMeetup(Long id) {
		Meetup meetup = meetups.findOne(id);
		Validate.notNull(meetup);
		
		meetup.setBanned(true);
		meetups.save(meetup);
		
		return OpResult.ok(meetup.getName() + " has been banned");
	}


}
