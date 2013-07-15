package com.mtg.web.dto;

import com.mtg.commons.models.Card;
import com.mtg.commons.models.CardMetadata;
import com.mtg.commons.models.Image;

public class CardDto {

    private long id;
    private String name;
    private String description;
    private String supertype;
    private String subtype;
    private CardMetadata metadata;
    private Image image;

    public CardDto(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.supertype = card.getSupertype();
        this.subtype = card.getSubtype();
        this.description = card.getDescription();
        this.metadata = card.getMetadata();
        this.image = card.getImage();
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
    public CardMetadata getMetadata() {
        return metadata;
    }
    public void setMetadata(CardMetadata metadata) {
        this.metadata = metadata;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    
}
