package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.locations.Country;

public interface CountryService extends JpaRepository<Country, Long>, CountryServiceCustom {

	Country findByName(String name);

}
