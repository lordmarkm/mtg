package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.Card;

public interface CardService extends JpaRepository<Card, Long> {

}
