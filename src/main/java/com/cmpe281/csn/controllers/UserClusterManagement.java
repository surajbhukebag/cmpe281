package com.cmpe281.csn.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.constants.StatusValue;
import com.cmpe281.csn.model.UserCluster;
import com.cmpe281.csn.repositories.ClusterRepository;
import com.cmpe281.csn.repositories.RoleRepository;
import com.cmpe281.csn.repositories.UserClusterRepository;
import com.cmpe281.csn.response.UserClusterResponse;

@CrossOrigin
@RestController
public class UserClusterManagement {
	
	@Autowired
	private UserClusterRepository userClusterRepository;
	
	@Autowired
	private ClusterRepository clusterRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@RequestMapping(value = "/usercluster/{id}", method = RequestMethod.POST)
	public UserClusterResponse createUser(@RequestBody UserCluster userCluster, @PathVariable("id") Integer id) {
		UserClusterResponse userResponse = new UserClusterResponse();
		try {
			userCluster.setCluster(clusterRepository.findOne(id));
			userCluster.getUser().setDateCreated(new Date().getTime());
			userCluster.getUser().setRole(roleRepository.findOne(userCluster.getUser().getRole().getId()));
			userCluster.setStatus(StatusValue.NEW.name());
			UserCluster savedUserCluster = userClusterRepository.save(userCluster);
			userResponse.setUserCluster(savedUserCluster);
			userResponse.setMsg("Successfully created User with id : "
					+ savedUserCluster.getId());
			userResponse.setCode("202");
		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

}
