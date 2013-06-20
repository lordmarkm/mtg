package com.mtg.security.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.mtg.security.models.Account;

public interface AccountService extends JpaRepository<Account, Long>, AccountServiceCustom {

	Account findByUsername(String name);

}
