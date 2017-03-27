package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.ForumDao;
import com.oa.domain.Forum;

@Repository
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements ForumDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findAll() {
		return getSession().createQuery("FROM Forum f ORDER BY f.position ASC")
				.getResultList();
	}

	@Override
	public void save(Forum entity) {
		getSession().save(entity);
		entity.setPosition(entity.getId().intValue());
	}


	@Override
	public Forum getPrevPositon(int id) {
		@SuppressWarnings("unchecked")
		List<Forum> forumList = getSession().createQuery(
				"FROM Forum f WHERE  f.position < :id ORDER BY f.position DESC"
				).setParameter("id", id)
				.setFirstResult(0)
				.setMaxResults(1)
				.getResultList();
		Forum forum = forumList.get(0);
		return forum;
	}
	
	@Override
	public Forum getNextPositon(int id) {
		@SuppressWarnings("unchecked")
		List<Forum> forumList = getSession().createQuery(
				"FROM Forum f WHERE  f.position > :id ORDER BY f.position ASC"
				).setParameter("id", id)
		.setFirstResult(0)
		.setMaxResults(1)
		.getResultList();
		Forum forum = forumList.get(0);
		return forum;
	}
	
	
}
