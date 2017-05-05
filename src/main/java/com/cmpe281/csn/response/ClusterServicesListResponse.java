package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.ClusterServices;

public class ClusterServicesListResponse extends CommonResponse {
	
	private List<ClusterServices> clusterServicesList;

	public List<ClusterServices> getClusterServicesList() {
		return clusterServicesList;
	}

	public void setClusterServicesList(List<ClusterServices> clusterServicesList) {
		this.clusterServicesList = clusterServicesList;
	}
	

}
