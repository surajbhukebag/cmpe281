package com.cmpe281.csn.response;

import com.cmpe281.csn.model.UserCluster;

public class UserClusterResponse extends CommonResponse {

	private UserCluster userCluster;

	public UserCluster getUserCluster() {
		return userCluster;
	}

	public void setUserCluster(UserCluster userCluster) {
		this.userCluster = userCluster;
	}
	
	
}
