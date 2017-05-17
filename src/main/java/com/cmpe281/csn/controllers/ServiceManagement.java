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

import com.cmpe281.csn.model.Service;
import com.cmpe281.csn.repositories.ServiceRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.ServiceListResponse;
import com.cmpe281.csn.response.ServiceResponse;

@CrossOrigin
@RestController
public class ServiceManagement {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/service", method = RequestMethod.POST)
	public ServiceResponse createService(@RequestBody Service service) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			service.setCreatedBy(userRepository.findOne(service.getCreatedBy()
					.getId()));
			service.setDateCreated(new Date().getTime());
			Service createdservice = serviceRepository.save(service);
			serviceResponse.setService(createdservice);
			serviceResponse.setMsg("Successfully created Service with id : "
					+ service.getId());
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

	@RequestMapping(value = "/service/{id}", method = RequestMethod.PUT)
	public ServiceResponse updateService(@RequestBody Service service,
			@PathVariable("id") Integer id) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			Service oldService = serviceRepository.findOne(id);
			oldService.setServiceName(service.getServiceName());
			Service updatedservice = serviceRepository.save(oldService);
			serviceResponse.setService(updatedservice);
			serviceResponse.setMsg("Successfully updated Service with id : "
					+ oldService.getId());
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

	@RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
	public ServiceResponse getService(@PathVariable("id") Integer id) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			Service service = serviceRepository.findOne(id);
			serviceResponse.setService(service);
			serviceResponse.setMsg("Successfully fetched Service with id : "
					+ service.getId());
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

	@RequestMapping(value = "/service", method = RequestMethod.GET)
	public ServiceListResponse getAllServices() {
		ServiceListResponse serviceResponse = new ServiceListResponse();
		try {
			Iterator<Service> serviceIterator = serviceRepository.findAll()
					.iterator();
			List<Service> serviceList = new ArrayList<Service>();
			while (serviceIterator.hasNext()) {
				serviceList.add(serviceIterator.next());
			}
			serviceResponse.setServices(serviceList);
			serviceResponse.setMsg("Successfully fetched Services");
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

	@RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE)
	public ServiceResponse deleteService(@PathVariable("id") Integer id) {
		ServiceResponse serviceResponse = new ServiceResponse();
		try {
			Service service = serviceRepository.findOne(id);
			service.setCreatedBy(null);
			serviceRepository.save(service);
			serviceRepository.delete(id);
			serviceResponse.setMsg("Successfully deleted Service ");
			serviceResponse.setCode("202");
		} catch (Exception e) {
			serviceResponse.setMsg("Something went wrong");
			serviceResponse.setCode("400");
		}

		return serviceResponse;
	}

}
