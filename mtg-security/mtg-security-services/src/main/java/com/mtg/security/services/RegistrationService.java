package com.mtg.security.services;

import com.mtg.security.models.Account;

public interface RegistrationService {

    Account register(String username, String password);
    
}
