package com.mtg.commons.services;

import com.mtg.commons.models.Card;


public class Util {

    public static Card wog() {
        Card card = new Card();
        card.setName("Wrath of God");
        card.setDescription("Destroy all creatures. They can't be regenerated");
        return card;
    }
    
}
