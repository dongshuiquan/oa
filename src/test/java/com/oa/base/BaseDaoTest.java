package com.oa.base;

import org.junit.Test;

import com.oa.dao.RoleDao;
import com.oa.dao.UserDao;
import com.oa.dao.impl.RoleDaoImpl;
import com.oa.dao.impl.UserDaoImpl;

public class BaseDaoTest {

	@SuppressWarnings("unused")
	@Test
	public void testBaseDao() {
		UserDao userDao = new UserDaoImpl();
		RoleDao roleDao = new RoleDaoImpl();
	}

}
