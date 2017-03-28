package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.cfg.Configuration;
import com.oa.dao.ReplyDao;
import com.oa.domain.PageBean;
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

	@Override
	public PageBean getPageBean(int pageNum, Topic topic) {
		int pageSize = Configuration.getPageSize();
		//查询本页的数据列表
		@SuppressWarnings("unchecked")
		List<Reply> result = getSession().createQuery(
				"FROM Reply r WHERE r.topic = ? ORDER BY r.postTime ASC"
				).setParameter(0, topic)
				.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		//查询总记录数
		@SuppressWarnings("unchecked")
		List<Long> countList = getSession().createQuery(
				"SELECT COUNT(*) FROM Reply r WHERE r.topic = ?"
				).setParameter(0, topic) 
				.getResultList();
		int count = countList.get(0).intValue();
		return new PageBean(pageNum, pageSize, result, count);
	}

}
