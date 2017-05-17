package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.UserMessages;

public class UserMessageListResponse extends CommonResponse {

	private List<UserMessages> userMessages;

	public List<UserMessages> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<UserMessages> userMessages) {
		this.userMessages = userMessages;
	}
	
	
}
