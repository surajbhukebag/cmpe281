package com.cmpe281.csn.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe281.csn.model.UserCluster;

public interface UserClusterRepository extends CrudRepository<UserCluster, Integer> {
	
    @Query(value="SELECT * FROM user_cluster u where u.cluster_id = ?1 and u.user_id=?2", nativeQuery = true) 
    UserCluster findByClusterAndUser(@Param("cluster_id") Integer cluster, @Param("user_id") Integer user);

}
