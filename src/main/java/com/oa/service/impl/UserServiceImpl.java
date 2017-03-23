package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.oa.dao.UserDao;
import com.oa.domain.User;
import com.oa.service.UserService;
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
	
}