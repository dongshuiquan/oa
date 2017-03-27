package com.oa.view.action;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;

import com.oa.domain.User;
import com.oa.service.DepartmentService;
import com.oa.service.ForumService;
import com.oa.service.PrivilegeService;
import com.oa.service.ReplyService;
import com.oa.service.RoleService;
import com.oa.service.TopicService;
import com.oa.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unchecked")
	public BaseAction() {
		//得到  model 的类型信息
		ParameterizedType pt = (ParameterizedType) this.getClass()/*子类对象*/.getGenericSuperclass();
		Class<?> clazz = (Class<?>) pt.getActualTypeArguments()[0];
		try {
			model =(T)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	protected T model;
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
	
	@Override
	public T getModel() {
		return model;
	}
	
	//获取当前用户
	protected User getCurrentUser(){
		return (User) ActionContext.getContext().getSession().get("user");
	}
}
