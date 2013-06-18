package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.magic.MagicPlayer;

/**
 * Move all MagicPlayer ops here, Account must be for auth only
 * @author Mark
 */
public interface PlayerService extends JpaRepository<MagicPlayer, Long>, PlayerServiceCustom {

}
