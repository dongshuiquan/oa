package com.oa.view.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public abstract class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T>{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unchecked")
	public ModelDrivenBaseAction() {
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
	
	@Override
	public T getModel() {
		return model;
	}
	
}	
	
