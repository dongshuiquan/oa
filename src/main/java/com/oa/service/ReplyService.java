package com.oa.service;

import java.util.List;

import com.oa.domain.PageBean;
import com.oa.domain.Reply;
import com.oa.domain.Topic;
import com.oa.util.HqlHelper;

public interface ReplyService {
	/**
	 * 查询指定主题中的所有回复列表， 排序： 最前面的是最早回复的回帖
	 * @param topic
	 * @return
	 */
	@Deprecated
	List<Reply> getByTopic(Topic topic);

	void save(Reply model);
	
	@Deprecated
	PageBean getPageBean(int pageNum, Topic topic);
	@Deprecated
	PageBean getPageBean(int pageNum, String hql, Object[] parameters);

	PageBean getPageBean(int pageNum, HqlHelper hqlHelper);

}
