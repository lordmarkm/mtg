package com.mtg.commons.models.collections;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.AbstractEntity;
import com.mtg.commons.models.Card;
import com.mtg.commons.models.magic.MagicPlayer;

@Entity
@Table(name = "decks")
public class Deck extends AbstractEntity {

	@ManyToMany
	private List<Card> cards;
	
	@ManyToOne
	private MagicPlayer owner;
	
	public String toString() {
		return new ToStringCreator(this)
			.append("name", name)
			.toString();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public MagicPlayer getOwner() {
		return owner;
	}

	public void setOwner(MagicPlayer owner) {
		this.owner = owner;
	}

}
