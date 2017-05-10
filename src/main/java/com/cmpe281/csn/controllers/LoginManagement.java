package com.cmpe281.csn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.User;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.request.LoginRequest;
import com.cmpe281.csn.response.LoginResponse;

@CrossOrigin
@RestController
public class LoginManagement {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponse validate(@RequestBody LoginRequest login) {

		LoginResponse loginResponse = new LoginResponse();
		try {

			User user = userRepository.findByUsername(login.getUsername());
			if (user.getPassword() != null
					&& user.getPassword().equals(login.getPassword())) {
				loginResponse.setCode("202");
				loginResponse.setMsg("User authenticated");
				loginResponse.setStatus(true);
				loginResponse.setUser(user);
			} else {

				loginResponse.setCode("500");
				loginResponse.setMsg("User not authenticated");
				loginResponse.setStatus(false);
			}
		} catch (Exception e) {

			loginResponse.setCode("500");
			loginResponse.setMsg("Something went wrong");
			loginResponse.setStatus(false);
		}

		return loginResponse;

	}
}
