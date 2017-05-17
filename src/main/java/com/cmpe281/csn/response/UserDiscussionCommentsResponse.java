package com.cmpe281.csn.response;

import com.cmpe281.csn.model.UserDiscussionComments;

public class UserDiscussionCommentsResponse extends CommonResponse {
	
	private UserDiscussionComments userDiscussionComments;

	public UserDiscussionComments getUserDiscussionComments() {
		return userDiscussionComments;
	}

	public void setUserDiscussionComments(
			UserDiscussionComments userDiscussionComments) {
		this.userDiscussionComments = userDiscussionComments;
	}
	
	

}
