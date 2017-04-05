package com.oa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 流转的表单文档
 * @author dong
 *
 */
@Entity
public class Application implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/** 状态常量， 审批中*/
	public static final String STATUS_RUNNIG = "审批中";
	
	/** 状态常量， 已通过*/
	public static final String STATUS_APPROVED = "已通过";
	
	/** 状态常量， 未通过*/
	public static final String STATUS_REJECTED= "未通过";
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private ApplicationTemplate template; //所使用的申请模板
	@OneToMany(mappedBy="application")
	private Set<ApproveInfo> approveInfos = new HashSet<>();;
	@ManyToOne
	private User applicant;	//申请人
	private String title;   //标题
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyTime;
	private String path;
	private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ApplicationTemplate getTemplate() {
		return template;
	}
	public void setTemplate(ApplicationTemplate template) {
		this.template = template;
	}
	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}
	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
