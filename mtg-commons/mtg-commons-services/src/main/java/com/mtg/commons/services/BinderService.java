package com.mtg.commons.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.collections.Binder;

public interface BinderService extends JpaRepository<Binder, Long>, BinderServiceCustom {

	/**
	 * Deprecated all delete methods for AbstractEntities use excise(..) instead!
	 */
	@Override
	@Deprecated
	void delete(Binder binder);
	
    @Query("from Binder where owner.name = :username and urlFragment = :urlFragment")
    Binder findByOwnerAndUrlFragment(@Param("username") String username,
            @Param("urlFragment") String urlFragment);

    @Query("from Binder b where :id in elements(b.owner.meetups)")
	List<Binder> findByMeetup(@Param("id") Long id);
    
    @Query("from Binder b where :id in elements(b.owner.cities)")
    List<Binder> findByCity(@Param("id") Long id);
    
    @Query("from Binder b where b.owner.country.id = :id")
	List<Binder> findByCountry(@Param("id") Long id);


}
