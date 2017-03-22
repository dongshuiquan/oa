
package com.oa.base;

import java.util.List;

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
}