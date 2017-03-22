package com.oa.service;

import java.util.List;

import com.oa.domain.Department;

public interface DepartmentService {

	List<Department> findAll();

	void delete(Long id);

	Department getById(Long id);

	void update(Department model);

	void save(Department model);

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
