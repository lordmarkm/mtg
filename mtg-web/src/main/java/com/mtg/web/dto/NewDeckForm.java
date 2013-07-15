package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.mtg.commons.models.collections.Deck;

public class NewDeckForm {

	@NotEmpty(message = "Deck must have a name!")
	private String name;
	
	@NotEmpty(message = "Deck must have a description!")
	private String description;

	public Deck toDeck() {
		Deck deck = new Deck();
		deck.setName(name);
		deck.setDescription(description);
		return deck;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
