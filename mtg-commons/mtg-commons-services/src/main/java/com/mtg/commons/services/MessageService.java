package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtg.commons.models.Message;

/**
 * @author Mark
 */
@Transactional
public interface MessageService extends JpaRepository<Message, Long> {
	Message findByKey(String key);

	@Modifying
	@Query("update Message m set message = :message where m.key = :key")
	void update(@Param("key") String key, @Param("message") String message);
}
