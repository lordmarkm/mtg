package com.mtg.interactive.chat.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.magic.MagicPlayer;
import com.mtg.interactive.chat.models.Chatroom;

public interface ChatroomService extends JpaRepository<Chatroom, Long> {

	@Query("from Chatroom c where c.type = 'personal' and :chatter1 in elements(c.chatters) and :chatter2 in elements(c.chatters)")
	Chatroom findPersonal(@Param("chatter1") MagicPlayer chatter1, @Param("chatter2") MagicPlayer chatter2);
	
}
