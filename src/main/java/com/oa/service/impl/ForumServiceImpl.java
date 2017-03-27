package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.ForumDao;
import com.oa.domain.Forum;
import com.oa.service.ForumService;
@Service
@Transactional
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumDao forumDao;
	@Override
	public List<Forum> findAll() {
		return forumDao.findAll();
	}
	@Override
	public void delete(Long id) {
		forumDao.delete(id);
	}
	@Override
	public void save(Forum model) {
		forumDao.save(model);
	}
	@Override
	public Forum getById(Long id) {
		return forumDao.getById(id);
	}
	@Override
	public void update(Forum model) {
		Forum forum = forumDao.getById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumDao.update(forum);
	}
	//上移， 最上面不能上移
	@Override
	public void moveUp(Long id) {
		Forum forum = forumDao.getById(id);
		Forum other = forumDao.getPrevPositon(forum.getPosition());
		if(other == null)
			return;
		int tmp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(tmp);
	}
	//下移， 最下面不能下移
	@Override
	public void moveDown(Long id) {
		Forum forum = forumDao.getById(id);
		Forum other = forumDao.getNextPositon(forum.getPosition());
		if(other == null)
			return;
		int tmp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(tmp);
	}
	
}
