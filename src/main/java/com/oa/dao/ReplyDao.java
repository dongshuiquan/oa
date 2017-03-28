package com.oa.dao;

import java.util.List;

import com.oa.base.BaseDao;
import com.oa.domain.PageBean;
import com.oa.domain.Reply;
import com.oa.domain.Topic;

public interface ReplyDao extends BaseDao<Reply>{

	List<Reply> getByTopic(Topic topic);

	PageBean getPageBean(int pageNum, Topic topic);

}
