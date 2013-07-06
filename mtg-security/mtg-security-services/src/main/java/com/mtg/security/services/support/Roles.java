package com.mtg.security.services.support;

import com.mtg.security.models.Account;

/**
 * HttpConfiguration automatically inserts 'ROLE_':
 * caused by: java.lang.IllegalArgumentException: role should not start with 'ROLE_' since it is automatically inserted. Got 'ROLE_ADMIN'
 * @author mbmartinez
 *
 */
public class Roles {

	public static final String ADMIN = "ADMIN";
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	public static boolean hasRole(Account account, String role) {
		if(null == account) {
			return false;
		}
		
		String[] auths = account.getAuthorities().split(", ");
		for(String auth : auths) {
			if(auth.trim().equals(role)) return true;
		}
		return false;
	}
	
}
