package com.mtg.interactive.posts.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mtg.commons.models.interactive.Frontpage;

public interface FrontpageService extends JpaRepository<Frontpage, Long> {

	@Query("from Frontpage where id = 1")
	Frontpage getFrontpage();

}
