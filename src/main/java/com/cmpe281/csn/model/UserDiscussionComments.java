package com.cmpe281.csn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class UserDiscussionComments {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=UserDiscussions.class, fetch=FetchType.EAGER)
	private UserDiscussions userDiscussion;
	
	@OneToOne(targetEntity=User.class, fetch=FetchType.EAGER)
	private User createdBy;
	
	private String comments;

	private long dateCreated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserDiscussions getUserDiscussion() {
		return userDiscussion;
	}

	public void setUserDiscussion(UserDiscussions userDiscussion) {
		this.userDiscussion = userDiscussion;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
}
