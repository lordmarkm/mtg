package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.collections.Wanted;

@Transactional
public interface WantedService extends JpaRepository<Wanted, Long>, WantedServiceCustom {

	@Modifying
	@Query("update Wanted w set w.count = w.count + 1, w.lastModified = now() where w.id = :id")
	void increment(@Param("id") Long id);

	@Modifying
	@Query("update Wanted w set w.count = w.count - 1, w.lastModified = now() where w.id = :id")
	void decrement(@Param("id") Long id);
	
	@Modifying
	@Query("update Wanted w set w.note = :note, w.lastModified = now() where w.id = :id")
	void editnote(@Param("id") Long id, @Param("note") String note);
	
}
