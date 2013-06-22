package com.mtg.commons.models.collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.magic.MagicPlayer;

/**
 * A bundle of Cards - a carggot
 * @author mbmartinez
 */
@Entity
@Table(name="wanted")
public class Wanted {

    @Id
    @GeneratedValue
    private long id;
    
    @ManyToOne(optional=false)
    private Card card;
    
    @ManyToOne
    private MagicPlayer wanter;
    
    @Column
    private int count;
    
    @Column
    @Type(type="text")
    private String note;

	@Column
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModified;
    
    public Wanted() {
    	//
    }
    
    public Wanted(Card card) {
    	this.card = card;
    	this.count = 1;
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
		Wanted other = (Wanted) obj;
		if (id != other.id)
			return false;
		return true;
	}
    
	public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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

	public MagicPlayer getWanter() {
		return wanter;
	}

	public void setWanter(MagicPlayer wanter) {
		this.wanter = wanter;
	}

	public DateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(DateTime lastModified) {
		this.lastModified = lastModified;
	}
    
}
