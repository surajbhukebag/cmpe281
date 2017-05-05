package com.cmpe281.csn.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.ServiceNode;
import com.cmpe281.csn.repositories.ServiceNodeRepository;
import com.cmpe281.csn.response.ServiceNodeListResponse;
import com.cmpe281.csn.response.ServiceNodeResponse;


@CrossOrigin
@RestController
public class ServiceNodeManagement {

	@Autowired
	private ServiceNodeRepository serviceNodeRepository;
	
	@RequestMapping(value = "/servicenode", method = RequestMethod.POST)
	public ServiceNodeResponse createServiceNode(@RequestBody ServiceNode serviceNode) {
		ServiceNodeResponse serviceNodeResponse = new ServiceNodeResponse();
		try {
			ServiceNode service = serviceNodeRepository.save(serviceNode);
			serviceNodeResponse.setServiceNode(service);
			serviceNodeResponse.setMsg("Successfully created Service node with id : "
					+ service.getId());
			serviceNodeResponse.setCode("202");
		} catch (Exception e) {
			serviceNodeResponse.setMsg("Something went wrong");
			serviceNodeResponse.setCode("400");
		}

		return serviceNodeResponse;
	}
	
	@RequestMapping(value = "/servicenode/{id}", method = RequestMethod.PUT)
	public ServiceNodeResponse updateServiceNode(@RequestBody ServiceNode serviceNode, @PathVariable("id") Integer id) {
		ServiceNodeResponse serviceNodeResponse = new ServiceNodeResponse();
		try {
			ServiceNode service = serviceNodeRepository.findOne(id);
			service.setName(serviceNode.getName());
			serviceNodeRepository.save(service);
			serviceNodeResponse.setServiceNode(service);
			serviceNodeResponse.setMsg("Successfully Updated Service node with id : "
					+ service.getId());
			serviceNodeResponse.setCode("202");
		} catch (Exception e) {
			serviceNodeResponse.setMsg("Something went wrong");
			serviceNodeResponse.setCode("400");
		}

		return serviceNodeResponse;
	}
	
	@RequestMapping(value = "/servicenode/{id}", method = RequestMethod.GET)
	public ServiceNodeResponse getServiceNode(@PathVariable("id") Integer id) {
		ServiceNodeResponse serviceNodeResponse = new ServiceNodeResponse();
		try {
			ServiceNode service = serviceNodeRepository.findOne(id);
			serviceNodeResponse.setServiceNode(service);
			serviceNodeResponse.setMsg("Successfully fetched service node");
			serviceNodeResponse.setCode("202");
		} catch (Exception e) {
			serviceNodeResponse.setMsg("Something went wrong");
			serviceNodeResponse.setCode("400");
		}

		return serviceNodeResponse;
	}
	
	@RequestMapping(value = "/servicenode", method = RequestMethod.GET)
	public ServiceNodeListResponse getAllServiceNode() {
		ServiceNodeListResponse serviceNodeResponse = new ServiceNodeListResponse();
		try {
			Iterator<ServiceNode> service = serviceNodeRepository.findAll().iterator();
			List<ServiceNode> serviceNodes = new ArrayList<ServiceNode>();
			while(service.hasNext()) {
				serviceNodes.add(service.next());
			}
			serviceNodeResponse.setServiceNodeList(serviceNodes);
			serviceNodeResponse.setMsg("Successfully fetched service node");
			serviceNodeResponse.setCode("202");
		} catch (Exception e) {
			serviceNodeResponse.setMsg("Something went wrong");
			serviceNodeResponse.setCode("400");
		}

		return serviceNodeResponse;
	}
	
	@RequestMapping(value = "/servicenode/{id}", method = RequestMethod.DELETE)
	public ServiceNodeResponse deleteServiceNode(@PathVariable("id") Integer id) {
		ServiceNodeResponse serviceNodeResponse = new ServiceNodeResponse();
		try {
			serviceNodeRepository.delete(id);
			serviceNodeResponse.setMsg("Successfully deleted service node");
			serviceNodeResponse.setCode("202");
		} catch (Exception e) {
			serviceNodeResponse.setMsg("Something went wrong");
			serviceNodeResponse.setCode("400");
		}

		return serviceNodeResponse;
	}
}
