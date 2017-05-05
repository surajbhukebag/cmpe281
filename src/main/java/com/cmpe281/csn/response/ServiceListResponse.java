package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.Service;

public class ServiceListResponse extends CommonResponse {

	private List<Service> services;

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	
}
