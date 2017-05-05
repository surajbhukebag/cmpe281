package com.cmpe281.csn.response;

import com.cmpe281.csn.model.User;

public class UserResponse extends CommonResponse {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
