package com.cmpe281.csn.repositories;

import org.springframework.data.repository.CrudRepository;

import com.cmpe281.csn.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
