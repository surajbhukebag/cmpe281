package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.User;
import com.cmpe281.csn.model.UserMessages;

public interface UserMessagesRepository extends
		CrudRepository<UserMessages, Integer> {
	
	 	@Query(value="SELECT * FROM user_messages u where u.to_id = ?1", nativeQuery = true) 
	    List<UserMessages> findByTo(@Param("to") Integer to);  

}
