package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.UserDiscussions;

public class UserDiscussionsListResponse extends CommonResponse {
	
	private List<UserDiscussions> userDiscussions;

	public List<UserDiscussions> getUserDiscussions() {
		return userDiscussions;
	}

	public void setUserDiscussions(List<UserDiscussions> userDiscussions) {
		this.userDiscussions = userDiscussions;
	}

	
}
