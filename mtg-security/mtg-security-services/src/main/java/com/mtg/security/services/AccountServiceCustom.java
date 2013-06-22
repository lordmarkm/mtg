package com.mtg.security.services;

import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Image;
import com.mtg.security.models.Account;

@Transactional
public interface AccountServiceCustom {
	/**
	 * @param account - must have id
	 * @return
	 */
	Account update(Account account);
	
	/**
	 * Update the the last login timestamp 
	 * @param username
	 */
	void updateLastLogin(String username);
	
	Image saveProfilePic(String name, byte[] bytes);
	
	boolean authenticate(String name);

	/**
	 * Resend verification email if user email not verified
	 * @return email address
	 */
	String resendVerification(String name);

}
