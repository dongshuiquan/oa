package com.oa.service;

import java.util.List;

import com.oa.domain.Reply;
import com.oa.domain.Topic;

public interface ReplyService {
	/**
	 * 查询指定主题中的所有回复列表， 排序： 最前面的是最早回复的回帖
	 * @param topic
	 * @return
	 */
	List<Reply> getByTopic(Topic topic);

	void save(Reply model);

}
