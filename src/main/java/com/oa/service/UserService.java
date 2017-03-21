
package com.oa.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.domain.User;

@Service
public class UserService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public void saveTwoUser(){
		Session session = sessionFactory.getCurrentSession();
		session.save(new User());
		Query<?> q = session.createNativeQuery("select last_insert_id()");
		System.out.println(q.getResultList().get(0));
//		@SuppressWarnings("unused")
//		int i = 1/0;
		session.save(new User());
	}
}