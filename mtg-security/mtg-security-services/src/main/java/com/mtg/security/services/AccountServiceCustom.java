package com.mtg.security.services;

import org.springframework.transaction.annotation.Transactional;

import com.mtg.security.models.Account;

public interface AccountServiceCustom {
	/**
	 * @param account - must have id
	 * @return
	 */
	@Transactional
	Account update(Account account);
}
