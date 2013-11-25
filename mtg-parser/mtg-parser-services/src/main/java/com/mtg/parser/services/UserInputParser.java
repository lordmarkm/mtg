package com.mtg.parser.services;

import com.mtg.commons.models.Card;

/**
 * Parse user inputted card names
 * @author mbmartinez
 */
public interface UserInputParser {

    /**
     * @param cardName
     * @return the Card if found, null if not found
     */
    Card parseUserInput(String cardName);

}
