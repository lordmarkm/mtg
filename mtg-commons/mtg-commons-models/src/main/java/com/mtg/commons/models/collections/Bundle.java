package com.mtg.commons.models.collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.mtg.commons.models.Card;

/**
 * A bundle of Cards - a carggot
 * @author mbmartinez
 */
@Entity
@Table(name="bundles")
public class Bundle {

    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne(optional=false)
    private Card card;
    
    @ManyToOne
    private BinderPage page;
    
    @Column
    private int count;
    
    @Column
    @Type(type="text")
    private String note;

    public Bundle() {
    	//
    }
    
    public Bundle(Card card) {
    	this.card = card;
    	this.count = 1;
	}

	public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public BinderPage getPage() {
        return page;
    }

    public void setPage(BinderPage page) {
        this.page = page;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bundle other = (Bundle) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
}
