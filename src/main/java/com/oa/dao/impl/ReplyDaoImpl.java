package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.dao.ReplyDao;
import com.oa.domain.Reply;
import com.oa.domain.Topic;

@Repository
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao{

	@Override
	public List<Reply> getByTopic(Topic topic) {
		@SuppressWarnings("unchecked")
		List<Reply> result = getSession().createQuery(
				"FROM Reply r WHERE r.topic = ? ORDER BY r.postTime ASC"
				).setParameter(0, topic)
				.getResultList();
		return result;
	}

}
