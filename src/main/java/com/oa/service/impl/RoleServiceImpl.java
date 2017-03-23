package com.oa.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.RoleDao;
import com.oa.domain.Role;
import com.oa.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> findAll() {
		List<Role> roleList = roleDao.findAll();
		return roleList;
	}

	@Override
	public void delete(Long id) {
		if(id == null)
			return;
		roleDao.delete(id);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public Role getById(Long id) {
		if(id == null)
			return new Role();
		return roleDao.getById(id);
	}

	@Override
	public void update(Role role) {
		Role oldRole = roleDao.getById(role.getId());
		oldRole.setName(role.getName());
		oldRole.setDescription(role.getDescription());
		roleDao.update(oldRole);
	}

	@Override
	public Set<Role> getbyIds(Long[] roleIds) {
		List<Role> list= roleDao.getByIds(roleIds);
		Set<Role> roleSet = new HashSet<>(list);
		return roleSet;
	}
	
}
