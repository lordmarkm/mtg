package com.mtg.commons.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="card")
public class Card extends AbstractEntity {

	@ManyToMany(mappedBy="cards")
	private List<Expansion> expansions;

	public List<Expansion> getExpansions() {
		if(null == expansions) {
			this.expansions = new ArrayList<Expansion>();
		}
		return expansions;
	}

	public void setExpansions(List<Expansion> expansions) {
		this.expansions = expansions;
	}
	
}
