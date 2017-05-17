package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.ServiceNode;

public interface ServiceNodeRepository extends CrudRepository<ServiceNode, Integer> {
	
	@Query(value="SELECT * FROM service_node u where u.created_by_id = ?1", nativeQuery = true) 
	List<ServiceNode> findByCreatedBy(@Param("createdBy") Integer createdBy);

}
