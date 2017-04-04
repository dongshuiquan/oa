package com.oa.service;

import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.ProcessDefinition;

public interface ProcessDefinitionService {
	
	/**
	 * 查询所有最新版本的流程定义列表
	 * @return
	 */
	List<ProcessDefinition> findAllLastestVersions();

	/**
	 * 根据 key 删除所有的版本
	 * @param key
	 */
	void deleteByKey(String key);

	/**
	 * 部署流程定义
	 * @param zipInputStream
	 */
	void deploy(ZipInputStream zipInputStream);

	/**
	 * 攻取指定流程定义的图片
	 * @param processDefinitionId 流程定义的 id
	 * @return
	 */
	InputStream getResourceImageResourceAsStream(String processDefinitionId);

}
