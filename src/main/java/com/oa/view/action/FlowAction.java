package com.oa.view.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Application;
import com.oa.domain.ApplicationTemplate;
import com.oa.domain.ApproveInfo;
import com.oa.domain.PageBean;
import com.oa.domain.TaskView;
import com.oa.domain.User;
import com.oa.util.HqlHelper;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author dong
 *
 */
@Controller
@Scope("prototype")
public class FlowAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Long templateId;
	private File upload;
	private Long applicationId;
	private String status;
	private String taskId;
	private String comment;
	private boolean approval;
	//=======================申请人的功能==================
	
	/**起草申请（申请模板列表)*/
	public String applicationTemplateList() throws Exception {
		List<ApplicationTemplate> templateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		return "templateList";
	}
	/**提交申请页面     */
	public String submitUI() throws Exception {
		return "submitUI";
	}
	/**提交申请         */
	public String submit() throws Exception {
		//封装对象
		Application application = new Application();
		// 1 表单中的参数
		application.setTemplate(applicationTemplateService.getById(templateId));
		application.setPath(saveUploadFile(upload));
		// 2 显示层的信息
		application.setApplicant(getCurrentUser());
		
		flowService.submit(application);
		return "toMyApplicationList";
	}
	/**我的申请查询     */
	public String myApplicationList() throws Exception {
		//准备分页信息
		User user = getCurrentUser();
		HqlHelper hqlHelper = new HqlHelper(Application.class, "a")
			.addCondition("a.applicant=?", user)
			.addCondition(templateId != null, "a.template.id=?", templateId)
			.addCondition(status != null && status.trim().length() > 0, 
				"a.status=?", status)
			.addOrder("a.applyTime", false);
		PageBean pageBean = replyService.getPageBean(pageNum, hqlHelper);	
		ActionContext.getContext().getValueStack().push(pageBean);
		//准备模板列表
		List<ApplicationTemplate> templateList = applicationTemplateService.findAll();
		ActionContext.getContext().put("templateList", templateList);
		return "myApplicationList";
	}
	
	//=======================审批人的功能==================
	
	/**待我审批         */
	public String myTaskList() throws Exception {
		List<TaskView> list = flowService.findMyTaskViewList(getCurrentUser());
		ActionContext.getContext().put("taskViewList", list);
		return "myTaskList";
	}
	/**审批处理页面     */
	public String approveUI() throws Exception {
		return "approveUI";
	}
	/**审批处理         */
	public String approve() throws Exception {
		//封装数据
		ApproveInfo approveInfo = new ApproveInfo();
		approveInfo.setApproval(approval);
		approveInfo.setComment(comment);
		approveInfo.setApplication(flowService.getApplicationById(applicationId));
		approveInfo.setApprover(getCurrentUser());
		approveInfo.setApproveTime(new Date());
		//调用业务方法（保存审批信息， 完成当前任务， 维护申请的状态)
		flowService.approve(approveInfo, taskId);
		return "toMyTaskList";
	}
	/**查看流转记录     */
	public String approvedHistory() throws Exception {
		List<ApproveInfo> approveInfoList = 
				flowService.getApproveInfosByApplicationId(applicationId);
		ActionContext.getContext().put("approveInfoList", approveInfoList);
		return "approvedHistory";
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isApproval() {
		return approval;
	}
	public void setApproval(boolean approval) {
		this.approval = approval;
	}
	
}
