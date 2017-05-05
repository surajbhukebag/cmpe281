package com.cmpe281.csn.response;

import com.cmpe281.csn.model.ServiceNode;

public class ServiceNodeResponse extends CommonResponse {

	private ServiceNode serviceNode;

	public ServiceNode getServiceNode() {
		return serviceNode;
	}

	public void setServiceNode(ServiceNode serviceNode) {
		this.serviceNode = serviceNode;
	}
	
	
}
