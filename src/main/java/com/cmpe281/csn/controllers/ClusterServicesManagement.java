package com.cmpe281.csn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.ClusterServices;
import com.cmpe281.csn.repositories.ClusterRepository;
import com.cmpe281.csn.repositories.ClusterServicesRepository;
import com.cmpe281.csn.repositories.ServiceRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.ClusterServiceResponse;
import com.cmpe281.csn.response.ClusterServicesListResponse;

@CrossOrigin
@RestController
public class ClusterServicesManagement {
	
	@Autowired
	private ClusterServicesRepository clusterServicesRepository;

	@Autowired
	private ClusterRepository clusterRepository;

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/clusterservice", method = RequestMethod.POST)
	public ClusterServiceResponse addServiceToCluster(@RequestBody ClusterServices clusterService) {
		ClusterServiceResponse serviceResponse = new ClusterServiceResponse();
		try {
			clusterService.setCluster(clusterRepository.findOne(clusterService.getCluster().getId()));
			clusterService.setResponsibleUser(userRepository.findOne(clusterService.getResponsibleUser().getId()));
			clusterService.setService(serviceRepository.findOne(clusterService.getService().getId()));
			ClusterServices createdservice = clusterServicesRepository.save(clusterService);
			serviceResponse.setClusterServices(createdservice);
			serviceResponse.setMsg("Successfully mapped Service to cluster");
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}
	
	@RequestMapping(value = "/clusterservice/{id}", method = RequestMethod.GET)
	public ClusterServicesListResponse getServicesForCluster(@PathVariable("id") Integer id) {
		ClusterServicesListResponse serviceResponse = new ClusterServicesListResponse();
		try {
			List<ClusterServices> clusterServices = clusterServicesRepository.findByCluster(id);
			serviceResponse.setClusterServicesList(clusterServices);
			serviceResponse.setMsg("Fetched services for a cluster");
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

}
