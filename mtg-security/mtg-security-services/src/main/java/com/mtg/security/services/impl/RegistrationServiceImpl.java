package com.mtg.security.services.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.models.Account;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private AccountService accounts;
    
    @Override
    public Account register(String username, String password) {
        
        MagicPlayer player = new MagicPlayer();
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setPlayer(player);

        return accounts.save(account);
    }

}
