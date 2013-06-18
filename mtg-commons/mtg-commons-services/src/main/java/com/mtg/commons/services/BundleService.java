package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtg.commons.models.collections.Bundle;

public interface BundleService extends JpaRepository<Bundle, Long> {

}
