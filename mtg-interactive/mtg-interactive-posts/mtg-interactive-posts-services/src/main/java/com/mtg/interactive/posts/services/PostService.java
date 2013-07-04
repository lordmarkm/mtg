package com.mtg.interactive.posts.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mtg.commons.models.interactive.Post;
import com.mtg.commons.models.interactive.PostParent.PostParentType;

public interface PostService extends JpaRepository<Post, Long>, PostServiceCustom {

	@Query("from Post p where p.deleted = 'false' and p.parent.parentType = :parentType and p.parent.parentId = :parentId order by p.postdate desc")
	List<Post> findByParent(@Param("parentType") PostParentType parentType, @Param("parentId") Long parentId,
			Pageable pageRequest);

	@Query("from Post p where (p.parent.parentType = 'frontpage' or " +
		   "(p.parent.parentType = 'city'    and p.parent.parentId in (:cityIds)) or " +
		   "(p.parent.parentType = 'meetup'  and p.parent.parentId in (:meetupIds)) or " +
		   "(p.parent.parentType = 'country' and p.parent.parentId = :countryId)) " +
		   "and p.deleted = 'false' " +
		   "order by p.postdate desc")
	List<Post> findByFrontpageOrLocation(@Param("cityIds") List<Long> cityIds, @Param("meetupIds") List<Long> meetupIds,
			@Param("countryId") Long countryId, Pageable pageRequest);

}
