package com.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 申请模板
 * @author dong
 *
 */
@Entity
public class ApplicationTemplate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String processDefinitionKey;
	private String path; //文件在服务器端存储的路径
	@OneToMany(mappedBy="template", cascade=CascadeType.ALL)
	private Set<Application> applications = new HashSet<>();
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
	
	public Set<Application> getApplications() {
		return applications;
	}
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
	@Override
	public String toString() {
		return "ApplicationTemplate [id=" + id + ", name=" + name + ", processDefinitionKey=" + processDefinitionKey
				+ ", path=" + path + "]";
	}
	
}
