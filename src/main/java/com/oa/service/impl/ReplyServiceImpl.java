package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.ForumDao;
import com.oa.dao.ReplyDao;
import com.oa.dao.TopicDao;
import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Reply;
import com.oa.domain.Topic;
import com.oa.service.ReplyService;
import com.oa.util.HqlHelper;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private ForumDao forumDao;
	@Autowired
	private TopicDao topicDao;
	
	@Override
	public List<Reply> getByTopic(Topic topic) {
		return replyDao.getByTopic(topic);
	}

	@Override
	public void save(Reply model) {
		replyDao.save(model);
		//维护相关的信息
		Topic topic = model.getTopic();
		Forum forum = topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount() + 1);
		topic.setReplyCount(topic.getReplyCount() + 1);
		topic.setLastReply(model);
		topic.setLastUpdateTime(model.getPostTime());
		
		forumDao.update(forum);
		topicDao.update(topic);
	}

	@Override
	@Deprecated
	public PageBean getPageBean(int pageNum, Topic topic) {
		return replyDao.getPageBean(pageNum, topic);
	}

	@Override
	public PageBean getPageBean(int pageNum, String hql, Object[] parameters) {
		return replyDao.getPageBean(pageNum, hql, parameters);
	}

	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		return replyDao.getPageBean(pageNum, hqlHelper);
	}

}
