package com.cmpe281.csn.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.UserMessages;
import com.cmpe281.csn.repositories.UserMessagesRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.UserMessageListResponse;
import com.cmpe281.csn.response.UserMessageResponse;

@CrossOrigin
@RestController
public class MessageManagement {
	
	@Autowired
	private UserMessagesRepository userMessagesRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/usermessage", method = RequestMethod.POST)
	public UserMessageResponse sendMessage(@RequestBody UserMessages userMessages) {
		
		UserMessageResponse userMessageResponse = new UserMessageResponse();
		try {
			userMessages.setFrom(userRepository.findOne(userMessages.getFrom().getId()));
			userMessages.setTo(userRepository.findOne(userMessages.getTo().getId()));
			userMessages.setDateCreated(new Date().getTime());
			UserMessages savedMsg = userMessagesRepository.save(userMessages);
			userMessageResponse.setUserMessages(savedMsg);
			userMessageResponse.setCode("202");
			userMessageResponse.setMsg("Successfullt sent message");
		}
		catch(Exception e) {
			userMessageResponse.setCode("400");
			userMessageResponse.setMsg("Something went wrong");
		}
		
		return userMessageResponse;
		
	}
	
	@RequestMapping(value = "/usermessage/{id}", method = RequestMethod.GET)
	public UserMessageListResponse getMessage(@PathVariable("id") Integer id) {
		
		UserMessageListResponse userMessageResponse = new UserMessageListResponse();
		try {
			
			List<UserMessages> msgs = userMessagesRepository.findByTo(id);
			userMessageResponse.setUserMessages(msgs);
			userMessageResponse.setCode("202");
			userMessageResponse.setMsg("Successfullt sent message");
		}
		catch(Exception e) {
			userMessageResponse.setCode("400");
			userMessageResponse.setMsg("Something went wrong");
		}
		
		return userMessageResponse;
		
	}

}
