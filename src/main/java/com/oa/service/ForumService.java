package com.oa.service;

import java.util.List;

import com.oa.domain.Forum;

public interface ForumService {

	List<Forum> findAll();

	void delete(Long id);

	void save(Forum model);

	Forum getById(Long id);

	void update(Forum model);

	void moveUp(Long id);

	void moveDown(Long id);

}
