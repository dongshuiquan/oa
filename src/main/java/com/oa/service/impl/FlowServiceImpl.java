package com.oa.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.dao.FlowDao;
import com.oa.domain.Application;
import com.oa.domain.ApproveInfo;
import com.oa.domain.TaskView;
import com.oa.domain.User;
import com.oa.service.FlowService;

@Service
@Transactional
public class FlowServiceImpl implements FlowService{

	@Autowired
	private FlowDao flowDao;
	@Autowired
	private ProcessEngine processEngine;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	
	@Override
	public void submit(Application application) {
		//1 设置属性并保存
		Date date = new Date();
		application.setApplyTime(date);
		application.setStatus(Application.STATUS_RUNNIG);
		application.setTitle(application.getTemplate().getName()
				+ "_" + application.getApplicant().getName()
				+ "_" + sdf.format(date)); // 标题格式，{模板名称}_{申请人姓名}_{申请日期}
		flowDao.submit(application);
		
		//2 启动流程， 开始流转
		// >> 启动流程实例， 设置流程变量 application
		String processKey = application.getTemplate().getProcessDefinitionKey();
		RuntimeService runtimeService  = processEngine.getRuntimeService();
		Map<String, Object> variables = new HashMap<>();
		variables.put("application", application);
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, variables);
		
		// >> 办理第一个任务 (提交申请 )
		Task task = processEngine.getTaskService()
				.createTaskQuery()
				.processInstanceId(pi.getId())
				.singleResult();
		processEngine.getTaskService().complete(task.getId());
	}

	@Override
	public List<TaskView> findMyTaskViewList(User currentUser) {
		//查询我的任务列表
		String userId = currentUser.getLoginName();
		List<Task> taskList = processEngine.getTaskService()
			.createTaskQuery()
			.taskAssignee(userId)
			.active()
			.list();	
		//获取每个任务对应的申请信息
		List<TaskView> returnList = new ArrayList<>();
		for (Task task : taskList) {
			Application application = (Application) processEngine.getTaskService()
					.getVariable(task.getId(), "application");
			TaskView taskView = new TaskView(task, application);
			returnList.add(taskView);
		}
		return returnList;
	}

	@Override
	public void approve(ApproveInfo approveInfo, String taskId) {
		// 1 保存审批信息
		flowDao.save(approveInfo);
		// 2 完成当前任务, 要先获取任务
		Task task = processEngine.getTaskService()
				.createTaskQuery()
				.taskId(taskId)
				.singleResult();
		processEngine.getTaskService().complete(taskId);
		//获取任务所属的流程实例， 如果流程已经结束， 则返回 null
		ProcessInstance pi = processEngine.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(task.getProcessInstanceId())
				.singleResult();
		// 3 维护申请的状态
		Application application = approveInfo.getApplication();
		if(!approveInfo.isApproval()){
			//如果本环节不同意， 则流程直接结束， 申请的状态为 “未通过”
			//如果本环节不是最后一个， 就要手工结束这个流程实例
			if(pi != null){
				//设置 结束流程标志， 在查询中过滤
				processEngine.getRuntimeService().suspendProcessInstanceById(pi.getId());
			}
			application.setStatus(Application.STATUS_REJECTED);
		}else{
			if(pi == null){
				//如果本环节同意了，并且是最后一个审批，则流程正常结束
				application.setStatus(Application.STATUS_APPROVED);
			}
		}
		flowDao.update(application);
	}

	@Override
	public Application getApplicationById(Long applicationId) {
		return flowDao.getApplicationById(applicationId);
	}

	@Override
	public List<ApproveInfo> getApproveInfosByApplicationId(Long applicationId) {
		return flowDao.getApproveInfosByApplicationId(applicationId);
	}

}
