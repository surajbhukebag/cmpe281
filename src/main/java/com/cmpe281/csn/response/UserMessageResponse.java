package com.cmpe281.csn.response;

import com.cmpe281.csn.model.UserMessages;

public class UserMessageResponse extends CommonResponse {
	
	private UserMessages userMessages;

	public UserMessages getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(UserMessages userMessages) {
		this.userMessages = userMessages;
	}
	

}
