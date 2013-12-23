package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtg.commons.models.Expansion;

public interface ExpansionService extends JpaRepository<Expansion, Long>, ExpansionServiceCustom {

	Expansion findByCode(String code);

	@Query("from Expansion e order by e.series desc")
	List<Expansion> findAll();
	
	@Deprecated//use excise(e) instead
	void delete(Expansion e);

}
