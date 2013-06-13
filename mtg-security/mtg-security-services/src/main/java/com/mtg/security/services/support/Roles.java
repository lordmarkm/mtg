package com.mtg.security.services.support;

/**
 * HttpConfiguration automatically inserts 'ROLE_':
 * caused by: java.lang.IllegalArgumentException: role should not start with 'ROLE_' since it is automatically inserted. Got 'ROLE_ADMIN'
 * @author mbmartinez
 *
 */
public class Roles {

	public static final String ADMIN = "ADMIN";
	
	public static final String ROLE_USER = "ROLE_USER";
	
}
