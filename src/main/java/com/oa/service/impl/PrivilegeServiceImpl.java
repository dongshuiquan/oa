package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.PrivilegeDao;
import com.oa.domain.Privilege;
import com.oa.service.PrivilegeService;
@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {
	@Autowired
	private PrivilegeDao privilegeDao;
	@Override
	public List<Privilege> findAll() {
		return privilegeDao.findAll();
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public Privilege getById(Long id) {
		return null;
	}

	@Override
	public void update(Privilege model) {

	}

	@Override
	public void save(Privilege model) {

	}

	@Override
	public List<Privilege> getByIds(Long[] privilegeIds) {
		return privilegeDao.getByIds(privilegeIds);
	}

	@Override
	public List<Privilege> findTopList() {
		return privilegeDao.findTopList();
	}

}
