package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.ForumDao;
import com.oa.dao.TopicDao;
import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Topic;
import com.oa.service.TopicService;

@Service
@Transactional
public class TopicServiceImpl implements TopicService{

	@Autowired
	private TopicDao topicDao;
	@Autowired
	private ForumDao forumDao;
	@Override
	@Deprecated 
	public List<Topic> findByForum(Forum forum) {
		return topicDao.findByForum(forum);
	}
	
	// 业务层的字段  type, replyCount, lastReply, lastUpdateTime
	@Override
	public void save(Topic model) {
		//1)设置属性
		//三个默认， 可以不设置
		model.setType(Topic.TYPE_NORMAL);
		model.setReplyCount(0);
		model.setLastReply(null);
		
		model.setLastUpdateTime(model.getPostTime());
		topicDao.save(model);
		
		//2）相关信息的维护
		Forum forum = model.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1);
		forum.setArticleCount(forum.getArticleCount()+ 1);
		forum.setLastTopic(model);
		forumDao.update(forum);
	}

	@Override
	public Topic getById(Long id) {
		return topicDao.getById(id);
	}

	@Override
	public PageBean getPageBean(int pageNum, Forum forum) {
		return topicDao.getPageBean(pageNum, forum);
	}

}
