package com.cmpe281.csn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.Cluster;

public interface ClusterRepository extends CrudRepository<Cluster, Integer> {
	
	

    @Query(value="SELECT * FROM cluster c where c.status_Value = ?1", nativeQuery = true) 
    List<Cluster> findByClusterStatus(@Param("statusValue") String statusValue);
    
    @Query(value="SELECT * FROM cluster c where c.admin_id = ?1 and c.status_value != ?2", nativeQuery = true) 
    List<Cluster> findByAdminAndStatus(@Param("admin") Integer admin, @Param("admin") String status);
	
}
