package com.cmpe281.csn.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe281.csn.model.Role;
import com.cmpe281.csn.repositories.RoleRepository;
import com.cmpe281.csn.response.RoleListResponse;
import com.cmpe281.csn.response.RoleResponse;

@CrossOrigin
@RestController
public class RoleManagement {

	@Autowired
	private RoleRepository roleRepository;

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public RoleResponse createCluster(@RequestBody Role role) {

		RoleResponse roleResponse = new RoleResponse();

		try {
			Role roleCreated = roleRepository.save(role);
			roleResponse.setRole(roleCreated);
			roleResponse.setCode("202");
			roleResponse
					.setMsg("Role created with id : " + roleCreated.getId());

		} catch (Exception e) {

			roleResponse.setCode("505");
			roleResponse.setMsg("Something went wrong");

		}
		return roleResponse;
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.PUT)
	public RoleResponse updateCluster(@RequestBody Role role, @PathVariable("id") Integer id) {

		RoleResponse roleResponse = new RoleResponse();

		try {
			Role oldRole = roleRepository.findOne(id);
			oldRole.setName(role.getName());
			roleRepository.save(oldRole);
			roleResponse.setRole(oldRole);
			roleResponse.setCode("202");
			roleResponse.setMsg("Role updated ");

		} catch (Exception e) {

			roleResponse.setCode("505");
			roleResponse.setMsg("Something went wrong");

		}
		return roleResponse;
	}

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public RoleListResponse getCluster() {

		RoleListResponse roleResponse = new RoleListResponse();

		try {
			Iterator<Role> roleIterator = roleRepository.findAll().iterator();
			List<Role> roles = new ArrayList<Role>();
			while (roleIterator.hasNext()) {
				roles.add(roleIterator.next());
			}
			roleResponse.setRoles(roles);
			roleResponse.setCode("202");
			roleResponse.setMsg("Roles fetched ");

		} catch (Exception e) {

			roleResponse.setCode("505");
			roleResponse.setMsg("Something went wrong");

		}
		return roleResponse;
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
	public RoleResponse getSpecificCluster(@PathVariable("id") Integer id) {

		RoleResponse roleResponse = new RoleResponse();

		try {
			Role role = roleRepository.findOne(id);
			if (role != null) {
				roleResponse.setRole(role);
				roleResponse.setCode("202");
				roleResponse.setMsg("Role fetched ");
			} else {
				roleResponse.setCode("505");
				roleResponse.setMsg("Something went wrong");
			}

		} catch (Exception e) {

			roleResponse.setCode("505");
			roleResponse.setMsg("Something went wrong");

		}
		return roleResponse;
	}

	@RequestMapping(value = "/role/{id}", method = RequestMethod.DELETE)
	public RoleResponse deleteSpecificCluster(@PathVariable("id") Integer id) {

		RoleResponse roleResponse = new RoleResponse();

		try {
			roleRepository.delete(id);
			roleResponse.setCode("202");
			roleResponse.setMsg("Role Deleted ");

		} catch (Exception e) {

			roleResponse.setCode("505");
			roleResponse.setMsg("Something went wrong");

		}
		return roleResponse;
	}

}
