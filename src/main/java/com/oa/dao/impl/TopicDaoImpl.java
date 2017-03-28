package com.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.oa.base.BaseDaoImpl;
import com.oa.cfg.Configuration;
import com.oa.dao.TopicDao;
import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Reply;
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

	@Override
	public PageBean getPageBean(int pageNum, Forum forum) {
		int pageSize = Configuration.getPageSize();
		//查询本页的数据列表
		@SuppressWarnings("unchecked")
		List<Reply> result = getSession().createQuery(
				"FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,"
						+ " t.lastUpdateTime DESC")
				.setParameter(0, forum)
				.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
		//查询总记录数
		@SuppressWarnings("unchecked")
		List<Long> countList = getSession().createQuery(
				"SELECT COUNT(*) FROM Topic t WHERE t.forum = ? ")
				.setParameter(0, forum) 
				.getResultList();
		int count = countList.get(0).intValue();
		return new PageBean(pageNum, pageSize, result, count);
	}

}
