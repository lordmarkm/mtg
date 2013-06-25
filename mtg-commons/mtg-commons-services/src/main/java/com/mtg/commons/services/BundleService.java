package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.collections.Bundle;

@Transactional
public interface BundleService extends JpaRepository<Bundle, Long>, BundleServiceCustom {

	@Modifying
	@Query("update Bundle b set b.count = b.count + 1, b.lastModified = now() where b.id = :id")
	void increment(@Param("id") Long id);

	@Modifying
	@Query("update Bundle b set b.count = b.count - 1, b.lastModified = now() where b.id = :id")
	void decrement(@Param("id") Long id);

	@Modifying
	@Query("update Bundle b set b.note = :note, b.lastModified = now() where b.id = :id")
    void editNote(@Param("id") Long id, @Param("note") String note);

}
