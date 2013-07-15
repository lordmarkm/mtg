package com.mtg.commons.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="expansions")
public class Expansion extends AbstractEntity {

	@Column(nullable=false)
	private String code;
	
	@Column
	private int series = 0;
	
	@Embedded
	private Rarities rarities;
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String abbreviation) {
		this.code = abbreviation;
	}

	public Rarities getRarities() {
		if(null == this.rarities) {
			this.rarities = new Rarities();
		}
		return rarities;
	}

	public void setRarities(Rarities rarities) {
		this.rarities = rarities;
	}

	public int getSeries() {
		return series;
	}

	public void setSeries(int series) {
		this.series = series;
	}

}
