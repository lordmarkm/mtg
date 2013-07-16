package com.mtg.web.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mtg.web.dto.JSON;

@Controller
@RequestMapping("/deck")
public interface DeckController {

    @ResponseBody
    @RequestMapping("/{id}/json")
    JSON json(Principal principal, Long id);

    @ResponseBody
    @RequestMapping("/{deckId}/add/{cardId}")
    JSON addCard(Principal principal, Long deckId, Long cardId);

}
