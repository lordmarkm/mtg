package com.mtg.commons.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="expansion")
public class Expansion extends AbstractEntity {

	@OneToMany(cascade=CascadeType.ALL, mappedBy="expansion")
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
