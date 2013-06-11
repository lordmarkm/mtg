package com.mtg.security.services.impl;

import javax.annotation.Resource;

import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.AccountServiceCustom;

public class AccountServiceCustomImpl implements AccountServiceCustom {

	@Resource
	private AccountService service;
	
	@Override
	public Account update(Account account) {
		Account old = service.findOne(account.getId());
		old.setUsername(account.getUsername());
		old.setPassword(account.getPassword());
		return old;
	}

}
