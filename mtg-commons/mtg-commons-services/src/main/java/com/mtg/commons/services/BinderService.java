package com.mtg.commons.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.collections.Binder;

public interface BinderService extends JpaRepository<Binder, Long>, BinderServiceCustom {

    @Query("from Binder where owner.name = :username and urlFragment = :urlFragment")
    Binder findByOwnerAndUrlFragment(@Param("username") String username,
            @Param("urlFragment") String urlFragment);

}
