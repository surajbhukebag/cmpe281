package com.cmpe281.csn.controllers;

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
import com.cmpe281.csn.repositories.UserClusterRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.UserClusterResponse;

@CrossOrigin
@RestController
public class UserClusterManagement {

	@Autowired
	private UserClusterRepository userClusterRepository;

	@Autowired
	private ClusterRepository clusterRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/usercluster", method = RequestMethod.POST)
	public UserClusterResponse createUser(@RequestBody UserCluster userCluster) {
		UserClusterResponse userResponse = new UserClusterResponse();
		try {
			UserCluster isExistCluster = userClusterRepository
					.findByClusterAndUser(userCluster.getCluster().getId(), userCluster.getUser().getId());
			if(isExistCluster != null) {
				userResponse.setMsg("User already added in cluster");
				userResponse.setCode("500");
			}
			else {
				userCluster.setCluster(clusterRepository.findOne(userCluster
						.getCluster().getId()));
				userCluster.setUser(userRepository.findOne(userCluster.getUser()
						.getId()));
				userCluster.setStatus(StatusValue.NEW.name());
				UserCluster savedUserCluster = userClusterRepository
						.save(userCluster);
				userResponse.setUserCluster(savedUserCluster);
				userResponse.setMsg("Successfully added User to cluster with id : "
						+ savedUserCluster.getId());
				userResponse.setCode("202");				
			}

		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/usercluster/{id}", method = RequestMethod.PUT)
	public UserClusterResponse activateUser(@PathVariable("id") Integer id) {
		UserClusterResponse userResponse = new UserClusterResponse();
		try {
			UserCluster userCluster = userClusterRepository.findOne(id);
			userCluster.setStatus(StatusValue.ACTIVE.name());
			UserCluster savedUserCluster = userClusterRepository
					.save(userCluster);
			userResponse.setUserCluster(savedUserCluster);
			userResponse.setMsg("Successfully updated cluster : "
					+ savedUserCluster.getId());
			userResponse.setCode("202");
		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/usercluster/{cluster_id}/{user_id}", method = RequestMethod.DELETE)
	public UserClusterResponse removeUser(
			@PathVariable("cluster_id") Integer cluster,
			@PathVariable("user_id") Integer user) {
		UserClusterResponse userResponse = new UserClusterResponse();
		try {
			UserCluster userCluster = userClusterRepository
					.findByClusterAndUser(cluster, user);
			userClusterRepository.delete(userCluster.getId());

			userResponse.setMsg("Successfully deleted Cluster");
			userResponse.setCode("202");
		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

}
