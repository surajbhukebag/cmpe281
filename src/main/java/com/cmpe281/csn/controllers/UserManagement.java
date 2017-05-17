package com.cmpe281.csn.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.constants.StatusValue;
import com.cmpe281.csn.model.User;
import com.cmpe281.csn.repositories.ClusterRepository;
import com.cmpe281.csn.repositories.RoleRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.UserListResponse;
import com.cmpe281.csn.response.UserResponse;
import com.cmpe281.csn.services.EmailService;

@CrossOrigin
@RestController
public class UserManagement {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ClusterRepository clusterRepository;

	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public UserResponse createUser(@RequestBody User user) {
		UserResponse userResponse = new UserResponse();
		try {
			User checkUsername = userRepository.findByUsername(user
					.getUsername());
			if (checkUsername != null) {
				userResponse.setMsg("Username Already Exists");
				userResponse.setCode("400");
			} else {
				user.setDateCreated(new Date().getTime());
				if(user.getCreatedBy() != null) {
					user.setCreatedBy(userRepository.findOne(user.getCreatedBy()
							.getId()));
				}				
				user.setRole(roleRepository.findOne(user.getRole().getId()));
				if (user.getCluster() != null
						&& user.getCluster().getId() != null) {
					user.setCluster(clusterRepository.findOne(user.getCluster()
							.getId()));
				}
				user.setStatus(StatusValue.ACTIVE.name());
				User savedUser = userRepository.save(user);
				userResponse.setUser(savedUser);
				userResponse.setMsg("Successfully created User with id : "
						+ savedUser.getId());
				userResponse.setCode("202");
				emailService.sendMail(savedUser.getEmail(), "User Creation",
						"User Created Successfully. Here is your username :"
								+ savedUser.getUsername() + " and password: "
								+ savedUser.getPassword());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public UserListResponse getUserList() {
		UserListResponse userResponse = new UserListResponse();
		try {
			Iterator<User> users = userRepository.findAll().iterator();
			List<User> userList = new ArrayList<User>();
			while (users.hasNext()) {
				userList.add(users.next());
			}
			userResponse.setUserList(userList);
			userResponse.setMsg("Totally there are " + userList.size()
					+ " users");
			userResponse.setCode("202");
		} catch (Exception e) {

			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	public UserResponse getUser(@PathVariable("userId") Integer userId) {
		UserResponse userResponse = new UserResponse();
		try {
			User user = userRepository.findOne(userId);
			userResponse.setUser(user);
			userResponse.setMsg("Successfully fetched User with id : "
					+ user.getId());
			userResponse.setCode("202");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	public UserResponse deleteUser(@PathVariable("userId") Integer userId) {
		UserResponse userResponse = new UserResponse();
		try {

			User user = userRepository.findOne(userId);
			user.setStatus(StatusValue.DELETED.name());
			userRepository.save(user);
			userResponse
					.setMsg("Successfully deleted User with id : " + userId);
			userResponse.setCode("202");
		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	public UserResponse updateUser(@RequestBody User user,
			@PathVariable("userId") Integer userId) {
		UserResponse userResponse = new UserResponse();
		try {
			User oldUser = userRepository.findOne(userId);
			oldUser.setRole(roleRepository.findOne(user.getRole().getId()));
			oldUser.setAddress(user.getAddress());
			oldUser.setContactNumber(user.getContactNumber());
			oldUser.setEmail(user.getEmail());
			oldUser.setFirstName(user.getFirstName());
			oldUser.setLastName(user.getLastName());
			oldUser.setUsername(user.getUsername());
			oldUser.setPassword(user.getPassword());
			if (user.getCluster() != null && user.getCluster().getId() != null) {
				oldUser.setCluster(clusterRepository.findOne(user.getCluster()
						.getId()));
			}
			User savedUser = userRepository.save(oldUser);
			userResponse.setUser(savedUser);
			userResponse.setMsg("Successfully updated User with id : "
					+ savedUser.getId());
			userResponse.setCode("202");
		} catch (Exception e) {
			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

	@RequestMapping(value = "/adminusers/{id}", method = RequestMethod.GET)
	public UserListResponse getAdminUserList(@PathVariable("id") Integer id) {
		UserListResponse userResponse = new UserListResponse();
		try {
			List<User> users = userRepository.findByCreatedByAndStatus(id,
					"ACTIVE");

			userResponse.setUserList(users);
			userResponse.setMsg("Totally there are " + users.size() + " users");
			userResponse.setCode("202");
		} catch (Exception e) {

			userResponse.setMsg("Something went wrong");
			userResponse.setCode("400");
		}

		return userResponse;
	}

}
