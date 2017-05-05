package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.ServiceNode;

public class ServiceNodeListResponse extends CommonResponse {

	private List<ServiceNode> serviceNodeList;

	public List<ServiceNode> getServiceNodeList() {
		return serviceNodeList;
	}

	public void setServiceNodeList(List<ServiceNode> serviceNodeList) {
		this.serviceNodeList = serviceNodeList;
	}
	
	
}
