package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtg.commons.models.locations.Meetup;

public interface MeetupService extends JpaRepository<Meetup, Long> {

	@Deprecated//use excise(m) instead
	void delete(Meetup m);
	
	@Query("from Meetup m where size(m.players) > 0 and m.banned != true")
	List<Meetup> findOccupiedNotBanned();
	
	/**
	 * Find by Meetup.urlFragment
	 */
    Meetup findByUrlFragment(String url);
	
}
