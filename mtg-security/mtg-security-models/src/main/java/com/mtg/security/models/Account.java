package com.mtg.security.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name=Account.TABLE_NAME)
public class Account {

	public static final String TABLE_NAME = "accounts";

	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true)
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String authorities;

	@OneToOne(cascade = CascadeType.ALL)
	private MagicPlayer player;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("id", id)
			.append("username", username)
			.append("authorities", authorities)
			.append("password", "[PROTECTED]")
			.toString();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public MagicPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MagicPlayer player) {
        this.player = player;
    }
	
}
