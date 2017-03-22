package com.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl<T> implements BaseDao<T> {
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
		System.out.println("clazz = " +  clazz.getName());
	}
	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> clazz;
	@Override
	public void save(T entity) {
		Session session = getSession();
		session.save(entity);
	}

	@Override
	public void delete(Long id) {
		Session session = getSession();
		Object obj = session.get(clazz, id);
		session.remove(obj);
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
	}

	@Override
	public T getById(Long id) {
		return (T)getSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(Long[] ids) {
		if(ids == null || ids.length == 0)
			return Collections.EMPTY_LIST;
		return (List<T>) getSession().createQuery(
				"FROM " + clazz.getSimpleName() + " WHERE id in (:ids)")
				.setParameterList("ids", ids)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return (List<T>) getSession().createQuery(
				"FROM " + clazz.getSimpleName())
				.getResultList();
	}
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
}
