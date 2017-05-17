package com.cmpe281.csn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class UserDiscussions {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(targetEntity = ClusterServices.class, fetch = FetchType.EAGER)
	private ClusterServices clusterServices;

	private String comments;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User createdBy;

	private long dateCreated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ClusterServices getClusterServices() {
		return clusterServices;
	}

	public void setClusterServices(ClusterServices clusterServices) {
		this.clusterServices = clusterServices;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User user) {
		this.createdBy = user;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

}
