package com.cmpe281.csn.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.constants.StatusValue;
import com.cmpe281.csn.model.Cluster;
import com.cmpe281.csn.repositories.AreaRepository;
import com.cmpe281.csn.repositories.ClusterRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.ClusterResponse;
import com.cmpe281.csn.response.CommonResponse;
import com.cmpe281.csn.response.GetAllClustersResponse;
import com.cmpe281.csn.services.EmailService;

@CrossOrigin
@RestController
public class ClusterManagement {

	@Autowired
	private ClusterRepository clusterRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/cluster", method = RequestMethod.POST)
	public ClusterResponse createCluster(@RequestBody Cluster requestedCluster) {
		requestedCluster.setDateCreated(new Date().getTime());
		requestedCluster.setStatusValue(StatusValue.NEW.name());
		requestedCluster.setAdmin(userRepository.findOne(requestedCluster
				.getAdmin().getId()));
		requestedCluster.setArea(areaRepository.findOne(requestedCluster
				.getArea().getId()));
		Cluster cluster = clusterRepository.save(requestedCluster);
		ClusterResponse clusterResponse = new ClusterResponse();
		clusterResponse.setCode("201");
		clusterResponse.setMsg("Successfully created Cluster with id : "
				+ cluster.getId());
		clusterResponse.setCluster(cluster);

		emailService.sendMail(cluster.getAdmin().getEmail(), "Cluster request",
				"Cluster creation request created");
		emailService.sendMail("moderator.neighborhood@gmail.com",
				"Cluster creation request",
				"Cluster creation request has been created by "
						+ cluster.getAdmin().getFirstName() + " "
						+ cluster.getAdmin().getLastName());

		return clusterResponse;
	}

	@RequestMapping(value = "/cluster/{id}", method = RequestMethod.PUT)
	public ClusterResponse updateCluster(@RequestBody Cluster cluster,
			@PathVariable("id") Integer id) {
		Cluster oldCluster = clusterRepository.findOne(id);
		ClusterResponse response = new ClusterResponse();
		if (oldCluster != null) {
			oldCluster.setName(cluster.getName());
			oldCluster.setAdmin(userRepository.findOne(cluster.getAdmin()
					.getId()));
			oldCluster.setArea(areaRepository
					.findOne(cluster.getArea().getId()));
			Cluster newCluster = clusterRepository.save(oldCluster);
			response.setCode("201");
			response.setMsg("Successfully updated Cluster with id : "
					+ newCluster.getId());
			response.setCluster(newCluster);
		} else {
			response.setCode("400");
			response.setMsg("Cluster Updation failed");
		}
		return response;
	}

	@RequestMapping(value = "/updatecluster/{id}", method = RequestMethod.PUT)
	public ClusterResponse updateClusterStatus(@PathVariable("id") Integer id) {
		Cluster oldCluster = clusterRepository.findOne(id);
		ClusterResponse response = new ClusterResponse();
		if (oldCluster != null) {
			oldCluster.setStatusValue(StatusValue.ACTIVE.name());
			Cluster newCluster = clusterRepository.save(oldCluster);
			response.setCode("201");
			response.setMsg("Successfully updated Cluster Status ");
			response.setCluster(newCluster);
			emailService.sendMail(newCluster.getAdmin().getEmail(), "Cluster Activated",
					"Cluster activation request processed");
		} else {
			response.setCode("400");
			response.setMsg("Cluster Updation failed");
		}
		return response;
	}

	@RequestMapping(value = "/cluster", method = RequestMethod.GET)
	public GetAllClustersResponse getClusterList() {
		Iterable<Cluster> clusterList = clusterRepository.findAll();
		GetAllClustersResponse response = new GetAllClustersResponse();
		if (clusterList != null && clusterList.iterator() != null) {
			Iterator<Cluster> iterator = clusterList.iterator();
			List<Cluster> list = new ArrayList<Cluster>();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}

			response.setCode("201");
			response.setMsg("Totally " + list.size() + " clusters available");
			response.setClusterList(list);

		} else {
			response.setCode("201");
			response.setMsg("No clusters available");
		}
		return response;
	}

	@RequestMapping(value = "/clustertobecreated", method = RequestMethod.GET)
	public GetAllClustersResponse getClusterToBeCreatedList() {
		List<Cluster> clusterList = clusterRepository
				.findByClusterStatus("NEW");
		GetAllClustersResponse response = new GetAllClustersResponse();
		if (clusterList != null) {
			response.setCode("201");
			response.setMsg("Totally " + clusterList.size()
					+ " clusters available");
			response.setClusterList(clusterList);

		} else {
			response.setCode("201");
			response.setMsg("No clusters available");
		}
		return response;
	}

	@RequestMapping(value = "/activeclusters", method = RequestMethod.GET)
	public GetAllClustersResponse getActiveCluster() {
		List<Cluster> clusterList = clusterRepository
				.findByClusterStatus("ACTIVE");
		GetAllClustersResponse response = new GetAllClustersResponse();
		if (clusterList != null) {
			response.setCode("201");
			response.setMsg("Totally " + clusterList.size()
					+ " clusters available");
			response.setClusterList(clusterList);

		} else {
			response.setCode("201");
			response.setMsg("No clusters available");
		}
		return response;
	}

	@RequestMapping(value = "/cluster/{id}", method = RequestMethod.GET)
	public ClusterResponse getCluster(@PathVariable("id") Integer id) {

		Cluster cluster = clusterRepository.findOne(id);
		ClusterResponse clusterResponse = new ClusterResponse();
		if (cluster != null) {
			clusterResponse.setCode("201");
			clusterResponse.setMsg("Successfully fetched Cluster with id : "
					+ cluster.getId());
			clusterResponse.setCluster(cluster);
		} else {
			clusterResponse.setCode("400");
			clusterResponse.setMsg("Cluster does not exist");

		}
		return clusterResponse;
	}

	@RequestMapping(value = "/cluster/{id}", method = RequestMethod.DELETE)
	public CommonResponse deleteCluster(@PathVariable("id") Integer id) {
		CommonResponse cr = new CommonResponse();
		Cluster oldCluster = clusterRepository.findOne(id);
		if (oldCluster != null) {
			oldCluster.setStatusValue(StatusValue.DELETED.name());
			clusterRepository.save(oldCluster);
			cr.setCode("201");
			cr.setMsg("Cluster Deleted");
		} else {
			cr.setCode("400");
			cr.setMsg("Cluster Deletion failed");
		}

		return cr;
	}

	@RequestMapping(value = "/adminclusters/{id}", method = RequestMethod.GET)
	public GetAllClustersResponse getAdminCluster(@PathVariable("id") Integer id) {
		List<Cluster> clusterList = clusterRepository.findByAdminAndStatus(id, "DELETED");
		GetAllClustersResponse response = new GetAllClustersResponse();
		if (clusterList != null) {
			response.setCode("201");
			response.setMsg("Totally " + clusterList.size()
					+ " clusters available");
			response.setClusterList(clusterList);

		} else {
			response.setCode("201");
			response.setMsg("No clusters available");
		}
		return response;
	}

}
