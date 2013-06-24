package com.mtg.commons.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import com.mtg.commons.models.collections.Bundle;
import com.mtg.commons.models.collections.Wanted;

/**
 * This card represents a conceptual card, rather than a concrete one. You can have 1000 paper
 * Wrath of Gods, but the card Wrath of God will be represented by only one instance of Card.
 * @author mbmartinez
 */
@Entity
@Table(name="card")
public class Card extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="exp")
	private Expansion expansion;

	@Embedded
	private CardMetadata metadata;
	
	@OneToMany(mappedBy="card", orphanRemoval=true)
	private List<Bundle> bundles;
	
	@OneToMany(mappedBy="card", orphanRemoval=true)
	private List<Wanted> wanteds;
	
	@Column
	private String cost;
	
	@Column
	private String supertype;
	
	@Column
	private String subtype;
	
	@Column
	private String rarity;
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("name", name)
			.append("description", description)
			.append("mana cost", cost)
			.append("supertype", supertype)
			.append("type", subtype)
			.append("expansion", expansion != null ? expansion.getName() : "None")
			.append("rarity", rarity)
			.append("image", image != null ? image.getPath() : "None")
			.append("image source", image != null ? image.getOriginalPath() : "None")
			.append("link", getMetadata().getCardlink())
			.toString();
	}
	
	public String[] costArray() {
		if(null == cost) {
			return new String[]{};
		} else if(cost.contains("{")) {
			return parseComplex(cost);
		} else {
			return cost.split("(?!^)");
		}
	}
	
	private String[] parseComplex(final String costString) {
		String complexCostPattern = "\\{(.*?)\\}";
		String simpleCost = costString.replaceAll(complexCostPattern, "");
		
		StringBuilder simpleCostString = new StringBuilder();
		char[] simpleCostArray = simpleCost.toCharArray();
		for(char c : simpleCostArray) {
			simpleCostString.append("{" + c + "}");
		}
		
		String splitcost = costString.replace(simpleCost, simpleCostString.toString());
		
		Pattern p = Pattern.compile(complexCostPattern);
		Matcher m = p.matcher(splitcost);
		
		List<String> costArray = new ArrayList<String>();
		while(m.find()) {
			costArray.add(m.group(1));
		}
		return costArray.toArray(new String[costArray.size()]);
	}
	
    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public List<Bundle> getBundles() {
    	if(null == bundles) {
    		this.bundles = new ArrayList<Bundle>();
    	}
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getSupertype() {
		return supertype;
	}

	public void setSupertype(String supertype) {
		this.supertype = supertype;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public CardMetadata getMetadata() {
		if(null == metadata) {
			metadata = new CardMetadata();
		}
		return metadata;
	}

	public void setMetadata(CardMetadata metadata) {
		this.metadata = metadata;
	}

	public List<Wanted> getWanteds() {
		if(null == wanteds) {
			this.wanteds = new ArrayList<Wanted>();
		}
		return wanteds;
	}

	public void setWanteds(List<Wanted> wanteds) {
		this.wanteds = wanteds;
	}

}
