package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtg.commons.models.locations.City;
import com.mtg.commons.models.locations.Country;

public interface CityService extends JpaRepository<City, Long> {

	@Deprecated //use excise(City) instead
	void delete(City city);
	
	@Query("from City c where size(c.players) > 0 and c.banned != true")
	List<Country> findOccupiedNotBanned();

	@Query("from City c where c.banned != true")
	List<City> findAllNotBanned();

	City findByUrlFragment(String cityUrl);
	
}
