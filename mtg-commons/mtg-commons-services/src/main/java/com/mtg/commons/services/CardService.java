package com.mtg.commons.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.Card;

public interface CardService extends PagingAndSortingRepository<Card, Long>, CardServiceCustom {

	@Query("select c from Card c where (c.expansion.code = :expcode)")
	Page<Card> findByExpCode(@Param("expcode") String expcode, Pageable request);

}
