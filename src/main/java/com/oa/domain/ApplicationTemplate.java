package com.oa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 申请模板
 * @author dong
 *
 */
@Entity
public class ApplicationTemplate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String processDefinitionKey;
	private String path; //文件在服务器端存储的路径
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String processDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitonKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ApplicationTemplate [id=" + id + ", name=" + name + ", processDefinitionKey=" + processDefinitionKey
				+ ", path=" + path + "]";
	}
	
}
