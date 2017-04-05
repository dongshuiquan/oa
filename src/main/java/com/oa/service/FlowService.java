package com.oa.service;

import java.util.List;

import com.oa.domain.Application;
import com.oa.domain.ApproveInfo;
import com.oa.domain.TaskView;
import com.oa.domain.User;

public interface FlowService {
	
	/**
	 * 1 保存申请信息
	 * 2 启动流程， 开始流转
	 * @param application
	 */
	void submit(Application application);

	/**
	 * 查询的的任务列表
	 * @param currentUser
	 * @return
	 */
	List<TaskView> findMyTaskViewList(User currentUser);

	/**
	 *  1 保存审批信息
	 *  2 完成当前任务
	 *  3 维护申请的状态
	 * @param approveInfo
	 * @param taskId
	 */
	void approve(ApproveInfo approveInfo, String taskId);

	/**
	 * 通过 id 获取申请信息
	 * @param applicationId
	 * @return
	 */
	Application getApplicationById(Long applicationId);

	/**
	 * 获取指定申请的所有流转记录(审批信息)
	 * @param applicationId
	 * @return
	 */
	List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId);

}
