package com.cmpe281.csn.response;

import com.cmpe281.csn.model.User;

public class LoginResponse extends CommonResponse {
	
	private boolean status;
	
	private User user;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
