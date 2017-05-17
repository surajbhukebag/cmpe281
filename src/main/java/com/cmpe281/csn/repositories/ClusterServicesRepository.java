package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.ClusterServices;

public interface ClusterServicesRepository extends CrudRepository<ClusterServices, Integer> {
	

    @Query(value="SELECT * FROM cluster_services cs where cs.cluster_id = ?1", nativeQuery = true) 
    List<ClusterServices> findByCluster(@Param("id") Integer id);
    
    @Query(value="SELECT * FROM cluster_services cs where cs.created_by_id = ?1", nativeQuery = true) 
    List<ClusterServices> findByCreatedBy(@Param("createdBy") Integer createdBy);

}
