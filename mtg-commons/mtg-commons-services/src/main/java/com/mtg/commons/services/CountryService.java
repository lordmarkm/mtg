package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtg.commons.models.locations.Country;

public interface CountryService extends JpaRepository<Country, Long>, CountryServiceCustom {

	Country findByName(String name);

	@Deprecated //use excise(country) instead
	void delete(Country country);

	@Query("from Country c where size(c.players) > 0")
	List<Country> findOccupied();

	Country findByUrlFragment(String urlFragment);
}
