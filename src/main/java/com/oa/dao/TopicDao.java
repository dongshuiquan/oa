package com.oa.dao;

import java.util.List;

import com.oa.base.BaseDao;
import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Topic;

public interface TopicDao extends BaseDao<Topic>{

	List<Topic> findByForum(Forum forum);

	PageBean getPageBean(int pageNum, Forum forum);

}
