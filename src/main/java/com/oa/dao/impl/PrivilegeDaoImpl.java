package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.PrivilegeDao;
import com.oa.domain.Privilege;

@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Privilege> findTopList() {
		return getSession().createQuery(
				"FROM Privilege p WHERE p.parent is NULL")
				.getResultList();
	}


}
