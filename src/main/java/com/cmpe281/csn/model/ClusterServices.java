package com.cmpe281.csn.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class ClusterServices {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@OneToOne(targetEntity = Cluster.class, fetch = FetchType.EAGER)
	private Cluster cluster;

	@OneToOne(targetEntity = ServiceNode.class, fetch = FetchType.EAGER)
	private ServiceNode serviceNode;

	private String name;

	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Role sender;

	@OneToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
	private Role receipient;

	private long dateCreated;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User createdBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	public ServiceNode getServiceNode() {
		return serviceNode;
	}

	public void setServiceNode(ServiceNode serviceNode) {
		this.serviceNode = serviceNode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getSender() {
		return sender;
	}

	public void setSender(Role sender) {
		this.sender = sender;
	}

	public Role getReceipient() {
		return receipient;
	}

	public void setReceipient(Role receipient) {
		this.receipient = receipient;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
