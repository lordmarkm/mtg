package com.mtg.commons.models;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Rarities {

	public enum Rarity {
		common, uncommon, rare, mythic
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image common;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image uncommon;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image rare;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Image mythic;

	public Image getCommon() {
		if(null == common) {
			this.common = new Image();
		}
		return common;
	}

	public void setCommon(Image common) {
		this.common = common;
	}

	public Image getUncommon() {
		if(null == uncommon) {
			this.uncommon = new Image();
		}
		return uncommon;
	}

	public void setUncommon(Image uncommon) {
		this.uncommon = uncommon;
	}

	public Image getRare() {
		if(null == rare) {
			this.rare = new Image();
		}
		return rare;
	}

	public void setRare(Image rare) {
		this.rare = rare;
	}

	public Image getMythic() {
		if(null == mythic) {
			this.mythic = new Image();
		}
		return mythic;
	}

	public void setMythic(Image mythic) {
		this.mythic = mythic;
	}
	
}
