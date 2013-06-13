package com.mtg.commons.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mtg.commons.models.collections.Bundle;

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

	@OneToMany(mappedBy="card")
	private List<Bundle> bundles;
	
    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }

}
