package com.cmpe281.csn.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe281.csn.model.Role;
import com.cmpe281.csn.model.User;
import com.cmpe281.csn.model.UserDiscussions;
import com.cmpe281.csn.repositories.UserRepository;

@Service
public class SmartNodeService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	public void handleUserRequest(UserDiscussions savedDiscussion) {
		if (savedDiscussion != null
				&& savedDiscussion.getClusterServices() != null
				&& savedDiscussion.getClusterServices().getReceipient() != null) {
			Role responsibleRole = savedDiscussion.getClusterServices()
					.getReceipient();
			List<User> responsibleUsers = userRepository
					.findByRole(responsibleRole.getId());
			for (User user : responsibleUsers) {
				if (user.getEmail() != null) {
					String msg = "User has posted new post related to "
							+ savedDiscussion.getClusterServices().getName()
							+ "Service in the Cluster : "
							+ savedDiscussion.getClusterServices().getCluster()
									.getName();
					emailService.sendMail(user.getEmail(),
							"New service Request Posted : By "
									+ savedDiscussion.getCreatedBy()
											.getFirstName(), msg);
				}

			}
		}

	}

}
