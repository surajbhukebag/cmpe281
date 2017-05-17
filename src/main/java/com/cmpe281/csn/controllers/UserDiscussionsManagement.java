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

import com.cmpe281.csn.model.UserDiscussionComments;
import com.cmpe281.csn.model.UserDiscussions;
import com.cmpe281.csn.repositories.ClusterServicesRepository;
import com.cmpe281.csn.repositories.UserDiscussionCommentsRepository;
import com.cmpe281.csn.repositories.UserDiscussionsRepository;
import com.cmpe281.csn.repositories.UserRepository;
import com.cmpe281.csn.response.UserComments;
import com.cmpe281.csn.response.UserCommentsResponse;
import com.cmpe281.csn.response.UserDiscussionCommentsResponse;
import com.cmpe281.csn.response.UserDiscussionsListResponse;
import com.cmpe281.csn.response.UserDiscussionsResponse;
import com.cmpe281.csn.services.SmartNodeService;

@CrossOrigin
@RestController
public class UserDiscussionsManagement {

	@Autowired
	private UserDiscussionsRepository userDiscussionsRepository;

	@Autowired
	private ClusterServicesRepository clusterServicesRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDiscussionCommentsRepository userDiscussionCommentsRepository;
	
	@Autowired
	private SmartNodeService smartNodeService;

	@RequestMapping(value = "/userdiscussion", method = RequestMethod.POST)
	public UserDiscussionsResponse createUserdiscussion(
			@RequestBody UserDiscussions userDiscussions) {
		UserDiscussionsResponse discussionResponse = new UserDiscussionsResponse();
		try {

			userDiscussions.setClusterServices(clusterServicesRepository
					.findOne(userDiscussions.getClusterServices().getId()));
			userDiscussions.setDateCreated(new Date().getTime());
			userDiscussions.setCreatedBy(userRepository.findOne(userDiscussions
					.getCreatedBy().getId()));
			UserDiscussions savedDiscussion = userDiscussionsRepository
					.save(userDiscussions);
			smartNodeService.handleUserRequest(savedDiscussion);
			
			discussionResponse.setUserDiscussions(savedDiscussion);
			discussionResponse
					.setMsg("Successfully created User Discussion with id : "
							+ savedDiscussion.getId());
			discussionResponse.setCode("202");

		} catch (Exception e) {
			discussionResponse.setMsg("Something went wrong");
			discussionResponse.setCode("400");
		}

		return discussionResponse;
	}

	@RequestMapping(value = "/userdiscussion/{id}", method = RequestMethod.GET)
	public UserDiscussionsListResponse getUserdiscussionsOfCluster(
			@PathVariable("id") Integer id) {
		UserDiscussionsListResponse discussionResponse = new UserDiscussionsListResponse();
		try {

			Iterator<UserDiscussions> discussions = userDiscussionsRepository
					.findAll().iterator();
			List<UserDiscussions> list = new ArrayList<UserDiscussions>();
			while (discussions.hasNext()) {
				
				UserDiscussions ud = discussions.next();
				
				if (ud != null && ud.getClusterServices() != null && ud.getClusterServices().getCluster() != null && id == ud.getClusterServices().getCluster().getId()) {
					list.add(ud);
				}
			}
			discussionResponse.setUserDiscussions(list);
			discussionResponse
					.setMsg("Successfully fetched Discussions for cluster");
			discussionResponse.setCode("202");

		} catch (Exception e) {
			System.out.println(e);
			discussionResponse.setMsg("Something went wrong");
			discussionResponse.setCode("400");
		}

		return discussionResponse;
	}

	@RequestMapping(value = "/commentuserdiscussion", method = RequestMethod.POST)
	public UserDiscussionCommentsResponse addCommentUserdiscussion(
			@RequestBody UserDiscussionComments userDiscussionComment) {
		UserDiscussionCommentsResponse discussionResponse = new UserDiscussionCommentsResponse();
		try {
			userDiscussionComment.setDateCreated(new Date().getTime());
			userDiscussionComment.setCreatedBy(userRepository
					.findOne(userDiscussionComment.getCreatedBy().getId()));
			userDiscussionComment
					.setUserDiscussion(userDiscussionsRepository
							.findOne(userDiscussionComment.getUserDiscussion()
									.getId()));
			UserDiscussionComments comment = userDiscussionCommentsRepository
					.save(userDiscussionComment);
			discussionResponse.setUserDiscussionComments(comment);
			discussionResponse
					.setMsg("Successfully added Comment for Discussion");
			discussionResponse.setCode("202");

		} catch (Exception e) {
			discussionResponse.setMsg("Something went wrong");
			discussionResponse.setCode("400");
		}

		return discussionResponse;
	}

	@RequestMapping(value = "/commentuserdiscussion/{id}", method = RequestMethod.GET)
	public UserCommentsResponse getCommentUserdiscussion(
			@PathVariable("id") Integer id) {
		UserCommentsResponse commentsResponse = new UserCommentsResponse();
		try {
			List<UserDiscussionComments> comments = userDiscussionCommentsRepository
					.findByUserDiscussion(id);
			List<UserComments> commentList = new ArrayList<UserComments>();
			for (UserDiscussionComments comment : comments) {
				UserComments com = new UserComments();
				com.setComment(comment.getComments());
				com.setCreatedBy(comment.getCreatedBy().getFirstName());
				com.setDate(comment.getDateCreated());
				
				commentList.add(com);
			}
			commentsResponse.setComments(commentList);
			commentsResponse
					.setMsg("Successfully added Comment for Discussion");
			commentsResponse.setCode("202");

		} catch (Exception e) {
			commentsResponse.setMsg("Something went wrong");
			commentsResponse.setCode("400");
		}

		return commentsResponse;
	}

}
