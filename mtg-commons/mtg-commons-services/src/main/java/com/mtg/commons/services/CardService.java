package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.Card;

public interface CardService extends PagingAndSortingRepository<Card, Long>, CardServiceCustom {

	@Query("select c from Card c where (c.expansion.code = :expcode)")
	Page<Card> findByExpCode(@Param("expcode") String expcode, Pageable request);

	@Query("from Card where name like :query")
	List<Card> searchByName(@Param("query") String query);

	List<Card> findByName(String name);
	
	@Deprecated
	void delete(Card card);

	@Query("from Card c where c.expansion.code = :expcode and c.name like :search")
	Page<Card> searchByExpCode(@Param("expcode") String expcode, @Param("search") String search,
			Pageable pageRequest);
}
