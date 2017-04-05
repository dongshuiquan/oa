package com.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.opensymphony.xwork2.ActionContext;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Department department;
	//多对多
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JoinTable(name="user_role",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	private String loginName;//登录名
	private String password;//密码
	private String name;//真实姓名
	private String gender;//性别
	private String phoneNumber;//电话号码
	private String email;//电子邮箱
	private String description;//说明
	
	public boolean hasPrivilegeByName(String privilegeName){
		//超级管理员有所有权限
		if(isAdmin()){
			return true;
		}
		//其他用户要是权限才返回true
		for(Role role : roles){
			for(Privilege privilege : role.getPrivileges()){
				if(privilege.getName().equals(privilegeName)){
					return true;
				}
			}
		}
		return false;
	}
	public boolean hasPrivilegeByUrl(String privilegeUrl) {
		if (isAdmin()) {
			return true;
		}

		if (privilegeUrl.endsWith("UI")) {
			privilegeUrl = privilegeUrl.substring(0, privilegeUrl.length() - 2);
		}

		@SuppressWarnings("unchecked")
		List<String> allPrivilegeUrls = (List<String>) ActionContext.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privilegeUrl)) {
			return true;
		} else {
			for (Role role : roles) {
				for (Privilege privilege : role.getPrivileges()) {
					if (privilegeUrl.equals(privilege.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}
	//是否是超级管理员
	private boolean isAdmin() {
		return "admin".equals(loginName);
	}
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", department=" + department + ", roles=" + roles + ", loginName=" + loginName
				+ ", password=" + password + ", name=" + name + ", gender=" + gender + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + ", description=" + description + "]";
	}
	
}
