package com.cmpe281.csn.response;

import java.util.List;

import com.cmpe281.csn.model.Role;

public class RoleListResponse extends CommonResponse {
	
	private List<Role> roles;

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	

}
