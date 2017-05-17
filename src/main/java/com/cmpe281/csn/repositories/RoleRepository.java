package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
    @Query(value="SELECT * FROM role u where u.created_by_id = ?1", nativeQuery = true) 
    List<Role> findByCreatedBy(@Param("createdBy") Integer createdBy);  

}
