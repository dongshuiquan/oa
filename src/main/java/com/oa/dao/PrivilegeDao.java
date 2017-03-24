package com.oa.dao;

import java.util.List;

import com.oa.base.BaseDao;
import com.oa.domain.Privilege;

public interface PrivilegeDao extends BaseDao<Privilege>{

	List<Privilege> findTopList();

	List<String> findAllPrivilegeUrls();

}
