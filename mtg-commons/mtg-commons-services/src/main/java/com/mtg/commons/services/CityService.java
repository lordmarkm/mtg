package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.locations.City;

public interface CityService extends JpaRepository<City, Long> {

}
