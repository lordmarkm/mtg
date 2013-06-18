package com.mtg.security.services.impl;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.security.models.Account;
import com.mtg.security.models.AccountInfo;
import com.mtg.security.services.AccountService;
import com.mtg.security.services.RegistrationService;
import com.mtg.security.services.support.Roles;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private AccountService accounts;
    
    @Override
    public Account register(String username, String password) {
        
        MagicPlayer player = new MagicPlayer();
        player.setName(username);
        
        AccountInfo info = new AccountInfo();
        info.setJoined(DateTime.now());
        
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setAuthorities(Roles.ROLE_USER);
        account.setPlayer(player);
        account.setInfo(info);

        return accounts.save(account);
    }

}
