package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.Cluster;

public class GetAllClustersResponse extends CommonResponse {
	
	private List<Cluster> clusterList;

	public List<Cluster> getClusterList() {
		return clusterList;
	}

	public void setClusterList(List<Cluster> clusterList) {
		this.clusterList = clusterList;
	}

}
