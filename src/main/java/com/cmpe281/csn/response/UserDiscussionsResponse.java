package com.cmpe281.csn.response;

import com.cmpe281.csn.model.UserDiscussions;

public class UserDiscussionsResponse extends CommonResponse {

	private UserDiscussions userDiscussions;

	public UserDiscussions getUserDiscussions() {
		return userDiscussions;
	}

	public void setUserDiscussions(UserDiscussions userDiscussions) {
		this.userDiscussions = userDiscussions;
	}
	
	
}
