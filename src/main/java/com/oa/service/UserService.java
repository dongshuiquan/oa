
package com.oa.service;

import java.util.List;

import com.oa.domain.PageBean;
import com.oa.domain.User;
import com.oa.util.HqlHelper;

public interface UserService{

	List<User> findAll();

	void delete(Long id);

	void save(User model);

	User getById(Long id);

	void update(User model);

	void initPassword(Long id);

	User checkByUsernameAndPassword(String username, String password);
	
	void addUser_20();

	PageBean getPageBean(int pageNum, HqlHelper hqlHelper);
}