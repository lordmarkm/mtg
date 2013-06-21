package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.Expansion;

public interface ExpansionService extends JpaRepository<Expansion, Long>, ExpansionServiceCustom {

	Expansion findByCode(String code);

	
	@Deprecated//use excise(e) instead
	void delete(Expansion e);
}
