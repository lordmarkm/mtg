package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.magic.MagicPlayer;

/**
 * Move all MagicPlayer ops here, Account must be for auth only
 * @author Mark
 */
public interface PlayerService extends JpaRepository<MagicPlayer, Long>, PlayerServiceCustom {

	@Modifying
	@Transactional
	@Query("update MagicPlayer m set m.contact = :contact where m.name = :name")
	void setContact(@Param("name") String name, @Param("contact") String contact);

	@Deprecated //really shouldn't be deleting players
	void delete(MagicPlayer p);
}
