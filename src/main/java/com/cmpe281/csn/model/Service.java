package com.cmpe281.csn.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class Service {
	
	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String serviceName;
	
	@OneToOne(targetEntity=ServiceNode.class,  cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private ServiceNode serviceNode;	

	@OneToOne(targetEntity=User.class,  cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private User createdBy;
	
	private long dateCreated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(long dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public ServiceNode getServiceNode() {
		return serviceNode;
	}

	public void setServiceNode(ServiceNode serviceNode) {
		this.serviceNode = serviceNode;
	}

	

}
