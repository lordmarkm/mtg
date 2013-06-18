package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.collections.BinderPage;

public interface PageService extends JpaRepository<BinderPage, Long> {

	/**
	 * Must be binderId not urlFragment since urlFragment uniqueness is not enforced
	 */
	@Query("from BinderPage b where b.binder.id = :binderId and b.pageNumber = :pageNumber")
	BinderPage findPage(@Param("binderId") Long binderId, @Param("pageNumber") int pageNumber);

}
