package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.Image;

public interface ImageService extends JpaRepository<Image, Long>, ImageServiceCustom {

}
