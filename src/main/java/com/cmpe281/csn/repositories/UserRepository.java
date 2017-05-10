package com.cmpe281.csn.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	

    @Query(value="SELECT * FROM user u where u.username = ?1", nativeQuery = true) 
    User findByUsername(@Param("username") String username);

}
