package com.mtg.commons.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CardMetadata {

	@Column
	private String cardlink;

	public String getCardlink() {
		return cardlink;
	}

	public void setCardlink(String cardlink) {
		this.cardlink = cardlink;
	}
	
}
