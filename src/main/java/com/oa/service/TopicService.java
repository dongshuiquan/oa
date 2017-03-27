package com.oa.service;

import java.util.List;

import com.oa.domain.Forum;
import com.oa.domain.Topic;

public interface TopicService {
	/**
	 * 查询指定版块中的主题列表， 排序，所有置顶贴都在最上面，
	 * 然后把最新状态的主题显示到前面 。
	 * @param forum
	 * @return
	 */
	List<Topic> findByForum(Forum forum);

	void save(Topic model);

	Topic getById(Long id);

}
