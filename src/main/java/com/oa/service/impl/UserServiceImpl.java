package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.oa.dao.UserDao;
import com.oa.domain.PageBean;
import com.oa.domain.User;
import com.oa.service.UserService;
import com.oa.util.HqlHelper;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public void save(User model) {
		String password = DigestUtils.md5DigestAsHex("1234".getBytes());
		model.setPassword(password);
		userDao.save(model);
	}

	@Override
	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public void update(User model) {
		User user = userDao.getById(model.getId());
		user.setDepartment(model.getDepartment());
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setGender(user.getGender());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setRoles(model.getRoles());
		userDao.update(user);
	}

	@Override
	public void initPassword(Long id) {
		User user = userDao.getById(id);
		String password = DigestUtils.md5DigestAsHex("1234".getBytes());
		user.setPassword(password);
		userDao.update(user);
	}

	@Override
	public User checkByUsernameAndPassword(String username, String password) {
		String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
		return userDao.checkByUsernameAndPassword(username, md5Password);
	}

	@Override
	public void addUser_20() {
		userDao.addUser_20();
	}

	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		return userDao.getPageBean(pageNum, hqlHelper);
	}
	
}
