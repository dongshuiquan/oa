package com.oa.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oa.dao.FlowDao;
import com.oa.domain.Application;
import com.oa.domain.ApproveInfo;

@Repository
public class FlowDaoImpl implements FlowDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void submit(Application application) {
		Session session = sessionFactory.getCurrentSession();
		session.save(application);
	}

	@Override
	public Application getApplicationById(Long applicationId) {
		return sessionFactory.getCurrentSession()
				.get(Application.class, applicationId);
	}

	@Override
	public void save(ApproveInfo approveInfo) {
		sessionFactory.getCurrentSession().save(approveInfo);
	}

	@Override
	public void update(Application application) {
		sessionFactory.getCurrentSession().update(application);
	}

	@Override
	public List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId) {
		@SuppressWarnings("unchecked")
		List<ApproveInfo> list = sessionFactory.getCurrentSession().createQuery(
				"FROM ApproveInfo a WHERE a.application.id=? ORDER BY a.approveTime ASC")
				.setParameter(0, applicationId)
				.getResultList();
		return list;
	}
}
