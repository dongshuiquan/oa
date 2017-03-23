package com.oa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.UserDao;
import com.oa.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public void delete(Long id) {
		Session session = getSession();
		
		/* user 表设置了cascade : remove ,会删除 role 角色， 造成删除错误，
		 1)、 使用 sql 语句
		session.createNativeQuery("DELETE FROM user_role WHERE user_id = :id")
			.setParameter("id", id)
			.executeUpdate();
		session.createNativeQuery("DELETE FROM user WHERE id = :id")
			.setParameter("id", id)
			.executeUpdate();*/
		
		//2)面向对角， 可移植性
		User user = session.get(User.class, id);
		user.setRoles(null);
		session.remove(user);
	}

	@Override
	public User checkByUsernameAndPassword(String username, String md5Password) {
		User user = null;
		@SuppressWarnings("unchecked")
		List<User> userList = getSession().createQuery("FROM User u WHERE u.loginName = :loginName "
				+ "and u.password = :password")
				.setParameter("loginName", username)
				.setParameter("password", md5Password)
				.getResultList();
		if(userList != null && userList.size() == 1){
			user = userList.get(0);
		}
		return user;
	}

}
