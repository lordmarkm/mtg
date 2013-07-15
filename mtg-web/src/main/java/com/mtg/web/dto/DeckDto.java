package com.mtg.web.dto;

import java.util.List;

import com.mtg.commons.models.Image;
import com.mtg.commons.models.collections.Deck;

public class DeckDto {

    private long id;
    private String name;
    private String description;
    private Image image;
    private List<CardDto> cards;
    
    public DeckDto(Deck deck) {
        this.id = deck.getId();
        this.name = deck.getName();
        this.description = deck.getDescription();
        this.image = deck.getImage();
        this.cards = DtoMaker.transform(deck.getCards());
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public List<CardDto> getCards() {
        return cards;
    }
    public void setCards(List<CardDto> cards) {
        this.cards = cards;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
