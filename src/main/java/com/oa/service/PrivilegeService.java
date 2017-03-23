package com.oa.service;

import java.util.List;

import com.oa.domain.Privilege;

public interface PrivilegeService {

	List<Privilege> findAll();

	void delete(Long id);

	Privilege getById(Long id);

	void update(Privilege model);

	void save(Privilege model);

	List<Privilege> getByIds(Long[] privilegeIds);

	List<Privilege> findTopList();

}
