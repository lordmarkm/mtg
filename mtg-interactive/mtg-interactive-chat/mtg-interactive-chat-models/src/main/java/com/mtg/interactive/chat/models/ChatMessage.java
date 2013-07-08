package com.mtg.interactive.chat.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name="chatmessages")
public class ChatMessage {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	private MagicPlayer sender;
	
	@ManyToOne
	private Chatroom chatroom;
	
	@Column
	@Type(type="text")
	private String text;
	
	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")    
	private DateTime timestamp;
	
	public MagicPlayer getSender() {
		return sender;
	}

	public void setSender(MagicPlayer sender) {
		this.sender = sender;
	}

	public Chatroom getChatroom() {
		return chatroom;
	}

	public void setChatroom(Chatroom chatroom) {
		this.chatroom = chatroom;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public DateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
