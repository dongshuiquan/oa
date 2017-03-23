package com.oa.view.action;

import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;

import com.oa.service.DepartmentService;
import com.oa.service.RoleService;
import com.oa.service.UserService;
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
	@Override
	public T getModel() {
		return model;
	}
}
