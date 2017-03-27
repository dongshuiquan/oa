package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.TopicDao;
import com.oa.domain.Forum;
import com.oa.domain.Topic;

@Repository
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> findByForum(Forum forum) {
		// 排序
		return getSession().createQuery(
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,"
				+ " t.lastUpdateTime DESC")
				.setParameter(0, forum)
				.getResultList();
	}

}
