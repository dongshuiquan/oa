package com.oa.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.oa.cfg.Configuration;
import com.oa.domain.PageBean;
import com.oa.util.HqlHelper;
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

	@Override
	public PageBean getPageBean(int pageNum, String queryListHQL, Object[] parameters) {
		int pageSize = Configuration.getPageSize();
		//查询本页的数据列表
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(queryListHQL);
		if(parameters != null && parameters.length > 0){
			for(int i = 0; i < parameters.length; i++){
				query.setParameter(i, parameters[i]);
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize);
		List<?> result = query.getResultList(); 
		//查询总记录数
		int index = queryListHQL.indexOf("ORDER BY");
		String countHQL = "SELECT COUNT(*) " + queryListHQL.substring(0, index);
		@SuppressWarnings("rawtypes")
		Query countQuery = getSession().createQuery(countHQL);
		if(parameters != null && parameters.length > 0){
			for(int i = 0; i < parameters.length; i++){
				countQuery.setParameter(i, parameters[i]);
			}
		}
		@SuppressWarnings("unchecked")
		List<Long> countList = countQuery.getResultList();
		int count = countList.get(0).intValue();
		return new PageBean(pageNum, pageSize, result, count);
	}

	@Override
	public PageBean getPageBean(int pageNum, HqlHelper hqlHelper) {
		int pageSize = Configuration.getPageSize();
		//查询本页的数据列表
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(hqlHelper.getQueryListHql());
		List<?> parameters = hqlHelper.getParameters();
		if(parameters != null && parameters.size() > 0){
			for(int i = 0; i < parameters.size(); i++){
				query.setParameter(i, parameters.get(i));
			}
		}
		query.setFirstResult((pageNum - 1) * pageSize)
				.setMaxResults(pageSize);
		List<?> result = query.getResultList(); 
		//查询总记录数
		String countHQL = hqlHelper.getQueryCountHql();
		@SuppressWarnings("rawtypes")
		Query countQuery = getSession().createQuery(countHQL);
		if(parameters != null && parameters.size() > 0){
			for(int i = 0; i < parameters.size(); i++){
				countQuery.setParameter(i, parameters.get(i));
			}
		}
		@SuppressWarnings("unchecked")
		List<Long> countList = countQuery.getResultList();
		int count = countList.get(0).intValue();
		return new PageBean(pageNum, pageSize, result, count);
	}
}
