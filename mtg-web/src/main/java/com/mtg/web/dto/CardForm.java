package com.mtg.web.dto;

import org.hibernate.validator.constraints.NotEmpty;

import com.mtg.commons.models.Card;

public class CardForm {

    @NotEmpty(message = "Card must have a name")
    private String name;
    
    private String description;
    
    @NotEmpty(message = "Card must have an expansion")
    private String expansion;
    
    public Card toCard() {
        Card card = new Card();
        card.setName(name);
        card.setDescription(null != description ? description : "" );
        return card;
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

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }
    
}
