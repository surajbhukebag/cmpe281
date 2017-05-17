package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.UserDiscussionComments;

public interface UserDiscussionCommentsRepository extends
		CrudRepository<UserDiscussionComments, Integer> {

	@Query(value = "SELECT * FROM user_discussion_comments u where u.user_discussion_id = ?1", nativeQuery = true)
	List<UserDiscussionComments> findByUserDiscussion(
			@Param("userDiscussion") Integer userDiscussion);

}
