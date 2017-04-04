package com.oa.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oa.service.ProcessDefinitionService;

@Service
@Transactional
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService {

	@Autowired
	private ProcessEngine processEngine;
	
//	@Autowired
//	private SessionFactory sessionFactory;

	@Override
	public List<ProcessDefinition> findAllLastestVersions() {
		//查询所有的流程定义列表， 把最新的版本都排到最后面
		List<ProcessDefinition> all = processEngine.getRepositoryService()
			.createProcessDefinitionQuery()
			.orderByProcessDefinitionVersion()
			.asc()
			.list();
		//2,过滤出所有最新的版本
		Map<String, ProcessDefinition> map = new HashMap<>();
		for(ProcessDefinition pd: all){
			map.put(pd.getKey(), pd);
		}
		return new ArrayList<ProcessDefinition>(map.values());
	}

	@Override
	public void deleteByKey(String key) {
		//1, 查询出指定 key 的所有版本的流程定义
		List<ProcessDefinition> list = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.processDefinitionKey(key)
				.list();
		//2, 循环删除
		for(ProcessDefinition pd : list){
			processEngine.getRepositoryService().deleteDeployment(pd.getDeploymentId(),true);
		}
	}

	@Override
	public void deploy(ZipInputStream zipInputStream) {
		processEngine.getRepositoryService()
			.createDeployment()
			.name("oa")
			.addZipInputStream(zipInputStream)
			.deploy();
	}

	@Override
	public InputStream getResourceImageResourceAsStream(String deploymentId) {
		// 根据 id 取出对应的流程定义对象
		ProcessDefinition pd = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.deploymentId(deploymentId)
				.singleResult(); 
		System.out.println(pd.getDeploymentId() + pd.getDiagramResourceName());
		InputStream inputStream = processEngine.getRepositoryService()
				.getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
		return inputStream;
	}
}
