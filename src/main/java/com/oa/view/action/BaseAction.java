package com.oa.view.action;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.domain.User;
import com.oa.service.ApplicationTemplateService;
import com.oa.service.DepartmentService;
import com.oa.service.FlowService;
import com.oa.service.ForumService;
import com.oa.service.PrivilegeService;
import com.oa.service.ProcessDefinitionService;
import com.oa.service.ReplyService;
import com.oa.service.RoleService;
import com.oa.service.TopicService;
import com.oa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	protected int pageNum = 1;
	
	@Autowired
	protected UserService userService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	protected DepartmentService departmentService;
	@Autowired
	protected PrivilegeService privilegeService;
	@Autowired
	protected ForumService forumService;
	@Autowired
	protected TopicService topicService;
	@Autowired
	protected ReplyService replyService;
	@Autowired
	protected ProcessDefinitionService processDefinitionService;
	@Autowired
	protected ApplicationTemplateService applicationTemplateService;
	@Autowired
	protected FlowService flowService;
	
	//获取当前用户
	protected User getCurrentUser(){
		return (User) ActionContext.getContext().getSession().get("user");
	}
	/**
	 * 	保存上传的文件, 并返回文件在服务器端的真实存储路径
	 * @param upload
	 * @return
	 */
	protected String saveUploadFile(File upload) {
		SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
		String basePath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/upload_files");
		String subPath = sdf.format(new Date());
		//如果文件不存在， 就创建
		File dir = new File(basePath + subPath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		String path = basePath + subPath + UUID.randomUUID().toString();
		upload.renameTo(new File(path));
		return path;
	}
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
