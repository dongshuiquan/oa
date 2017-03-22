package com.oa.service;

import java.util.List;

import com.oa.domain.Role;

public interface RoleService {
	/**
	 * 查询所有岗位
	 * @return
	 */
	List<Role> findAll();

	void delete(Long id);

	void save(Role role);

	Role getById(Long id);

	void update(Role role);

}
