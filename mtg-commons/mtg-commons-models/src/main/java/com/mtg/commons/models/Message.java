package com.mtg.commons.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, unique=true, name="message_key")
	private String key;
	
	@Column(name="description")
	@Type(type="text")
	private String description;
	
	@Column(nullable=false, name="message")
	@Type(type="text")
	private String message;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("key", key)
			.append("message", message)
			.toString();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
