package com.oa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.DepartmentDao;
import com.oa.domain.Department;
import com.oa.service.DepartmentService;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<Department> findAll() {
		List<Department> list = departmentDao.findAll();
		return list;
	}
	
	@Override
	public List<Department> findTopList() {
		List<Department> list = departmentDao.findTopList();
		return list;
	}
	
	@Override
	public List<Department> findChildren(Long parentId) {
		List<Department> list = departmentDao.findChildren(parentId);
		return list;
	}
	
	@Override
	public void delete(Long id) {
		departmentDao.delete(id);
	}

	@Override
	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	@Override
	public void update(Department model) {
		Department department = departmentDao.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(model.getParent());
		departmentDao.update(department);
	}

	@Override
	public void save(Department entity) {
		departmentDao.save(entity);
	}

	

	
	
}
