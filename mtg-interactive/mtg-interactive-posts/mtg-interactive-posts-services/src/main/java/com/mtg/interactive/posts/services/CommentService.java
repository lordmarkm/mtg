package com.mtg.interactive.posts.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.interactive.Comment;
import com.mtg.commons.models.magic.MagicPlayer;

public interface CommentService extends JpaRepository<Comment, Long>, CommentServiceCustom {

	@Query("from Comment c where c.author = :author and c.deleted = 'false' order by c.postdate desc")
	Page<Comment> findByAuthor(@Param("author") MagicPlayer player, Pageable pageable);

}
