package com.mtg.security.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.security.models.Account;

public interface AccountService extends JpaRepository<Account, Long>, AccountServiceCustom {

	Account findByUsername(String name);

	@Query("from Account a where a.info.email = :email")
    Account findByEmail(@Param("email") String email);

	@Query("from Account a where a.info.authenticationCode = :auth")
	Account findByAuthenticationCode(@Param("auth") String authenticationCode);

	@Query("from Account a where a.player.id = :id")
	Account findByPlayerId(@Param("id") long id);

}
