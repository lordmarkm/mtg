package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.locations.Meetup;

public interface MeetupService extends JpaRepository<Meetup, Long> {

}
