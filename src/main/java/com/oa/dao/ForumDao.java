package com.oa.dao;

import com.oa.base.BaseDao;
import com.oa.domain.Forum;

public interface ForumDao extends BaseDao<Forum>{

	Forum getNextPositon(int position);
	
	Forum getPrevPositon(int position);

}
