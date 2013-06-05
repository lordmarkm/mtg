package com.mtg.commons.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="expansion")
public class Expansion extends AbstractEntity {

	@ManyToMany
	@JoinTable(
		name="expansion_card",
		joinColumns= {@JoinColumn(name="expansion_id")},
		inverseJoinColumns={@JoinColumn(name="card_id")}
	)
	private List<Card> cards;

	public List<Card> getCards() {
		if(null == cards) {
			this.cards = new ArrayList<Card>();
		}
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
}
