package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	

    @Query(value="SELECT * FROM user u where u.username = ?1", nativeQuery = true) 
    User findByUsername(@Param("username") String username);
    
    @Query(value="SELECT * FROM user u where u.created_by_id = ?1 and u.status = ?2", nativeQuery = true) 
    List<User> findByCreatedByAndStatus(@Param("createdBy") Integer createdBy, @Param("status") String status);  
    
    
    @Query(value="SELECT * FROM user u where u.status = ?1", nativeQuery = true) 
    List<User> findByStatus(@Param("createdBy") String status);
    
    @Query(value="SELECT * FROM user u where u.role_id = ?1", nativeQuery = true) 
    List<User> findByRole(@Param("roleId") Integer roleId);
    
    
    

}
