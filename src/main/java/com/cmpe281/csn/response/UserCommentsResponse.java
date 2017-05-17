package com.cmpe281.csn.response;

import java.util.List;

public class UserCommentsResponse extends CommonResponse {

	private List<UserComments> comments;

	public List<UserComments> getComments() {
		return comments;
	}

	public void setComments(List<UserComments> comments) {
		this.comments = comments;
	}
	
	
}
