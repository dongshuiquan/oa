package com.oa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.DepartmentDao;
import com.oa.domain.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

	@Override
	public List<Department> findTopList() {
		Session session= getSession();
		@SuppressWarnings("unchecked")
		List<Department> list = session.createQuery("FROM Department d where d.parent is empty").getResultList();
		return list;
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		Session session= getSession();
		@SuppressWarnings("unchecked")
		List<Department> list = session.createQuery("FROM Department d where d.parent.id = :parentId")
		.setParameter("parentId", parentId)
		.getResultList();
		return list;
	}

}
