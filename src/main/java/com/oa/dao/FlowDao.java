package com.oa.dao;

import java.util.List;

import com.oa.domain.Application;
import com.oa.domain.ApproveInfo;

public interface FlowDao{
	void submit(Application application);

	Application getApplicationById(Long applicationId);

	void save(ApproveInfo approveInfo);

	void update(Application application);

	List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId);
}
