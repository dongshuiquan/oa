
package com.oa.base;

import java.util.List;

import com.oa.domain.PageBean;
import com.oa.util.HqlHelper;

public interface BaseDao<T>{
	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);
	
	/**
	 * 删除实体
	 * @param id
	 */
	void delete(Long id);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 查询单个实体
	 * @param id
	 * @return
	 */
	
	T getById(Long id);
	/**
	 * 根据数组查询相应实体
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Long[] ids);
	
	/**
	 * 查询所有
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 公共的查询分页信息的方法
	 * @param pageNum
	 * @param queryListHQL 查询列表的查询语句， 前面加上 （“SELECT COUNT(*) "
	 * 			就是查询数量的 HQL 语句
	 * @param parameters 参数列表， 顺序与  queryListHQL 中的 ‘？’ 的顺序一一对应
	 * @return
	 */
	PageBean getPageBean(int pageNum, String queryListHQL, Object[] parameters);
	
	PageBean getPageBean(int pageNum, HqlHelper hqlHelper);
}