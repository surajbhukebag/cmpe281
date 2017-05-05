package com.cmpe281.csn.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

@Entity
public class ClusterServices {

	@javax.persistence.Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@OneToOne(targetEntity=Cluster.class,  cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Cluster cluster;
	
	@OneToOne(targetEntity=Service.class,  cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Service service;
	
	@OneToOne(targetEntity=User.class,  cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private User responsibleUser;

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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public User getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(User responsibleUser) {
		this.responsibleUser = responsibleUser;
	}
	
	
	
}
