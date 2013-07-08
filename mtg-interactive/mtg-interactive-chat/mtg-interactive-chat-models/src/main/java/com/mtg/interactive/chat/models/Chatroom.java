package com.mtg.interactive.chat.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="chatrooms")
public class Chatroom {

	public enum Type {
		personal, //1 on 1 chat
		group //group chat
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="chatroom")
	@OrderBy("timestamp desc")
	private List<ChatMessage> messages;

	@ManyToMany
	private List<MagicPlayer> chatters;
	
	@Enumerated(EnumType.STRING)
	private Type type;

	public String toString() {
		return new ToStringCreator(this)
			.append("type", type)
			.append("chatters", chatters)
			.toString();
	}
	
	public List<ChatMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<ChatMessage> messages) {
		this.messages = messages;
	}

	public List<MagicPlayer> getChatters() {
		if(null == chatters) {
			this.chatters = new ArrayList<MagicPlayer>();
		}
		return chatters;
	}

	public void setChatters(List<MagicPlayer> chatters) {
		this.chatters = chatters;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
