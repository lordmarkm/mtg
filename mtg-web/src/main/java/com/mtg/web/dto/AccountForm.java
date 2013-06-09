package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.core.style.ToStringCreator;

import com.mtg.security.models.Account;

public class AccountForm {

	private Long id;
	
	@NotEmpty(message = "Username is required")
	private String username;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	@NotEmpty(message = "Confirm password is required")
	private String confirmpw;

	public AccountForm() {
		//
	}
	
	public AccountForm(Account account) {
		this.id = account.getId();
		this.username = account.getUsername();
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("id", id)
			.append("username", username)
			.toString();
	}
	
	public Account getAccount() {
		Account account = new Account();
		account.setId(id);
		account.setUsername(username);
		account.setPassword(password);
		return account;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmpw() {
		return confirmpw;
	}

	public void setConfirmpw(String confirmpw) {
		this.confirmpw = confirmpw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
