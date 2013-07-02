package com.mtg.interactive.posts.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.interactive.Comment;

public interface CommentService extends JpaRepository<Comment, Long>, CommentServiceCustom {

}
