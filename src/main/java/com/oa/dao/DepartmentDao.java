package com.oa.dao;

import java.util.List;

import com.oa.base.BaseDao;
import com.oa.domain.Department;

public interface DepartmentDao extends BaseDao<Department>{

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

}
