package com.oa.dao;

import com.oa.base.BaseDao;
import com.oa.domain.User;

public interface UserDao extends BaseDao<User>{

	User checkByUsernameAndPassword(String username, String md5Password);

	void addUser_20();

}
