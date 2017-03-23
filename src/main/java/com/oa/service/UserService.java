
package com.oa.service;

import java.util.List;

import com.oa.domain.User;

public interface UserService{

	List<User> findAll();

	void delete(Long id);

	void save(User model);

	User getById(Long id);

	void update(User model);

	void initPassword(Long id);
	
}