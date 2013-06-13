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
    
    @ManyToOne
    private Card card;
    
    @ManyToOne
    private Binder binder;
    
    @Column
    private int number;
    
    @Column
    @Type(type="text")
    private String note;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Binder getBinder() {
        return binder;
    }

    public void setBinder(Binder binder) {
        this.binder = binder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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
    
}
