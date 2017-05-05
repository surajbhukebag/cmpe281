package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.User;

public class UserListResponse extends CommonResponse {

	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}
