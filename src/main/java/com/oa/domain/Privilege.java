package com.oa.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * 权限
 * @author dong
 */
@Entity
public class Privilege implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String name;
	private String icon; //图标， 顶级菜单用
	@ManyToMany(mappedBy="privileges", fetch=FetchType.EAGER)
	private Set<Role> roles = new HashSet<>();
	@ManyToOne
	private Privilege parent;
	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
	@OrderBy("id")
	private Set<Privilege> children = new HashSet<>();
	
	public Privilege() {
	}
	
	public Privilege(String name, String url,  String icon, Privilege parent) {
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public Privilege getParent() {
		return parent;
	}
	public void setParent(Privilege parent) {
		this.parent = parent;
	}
	public Set<Privilege> getChildren() {
		return children;
	}
	public void setChildren(Set<Privilege> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Privilege [id=" + id + ", url=" + url + ", name=" + name + ", icon=" + icon + ", roles=" + roles
				+ ", parent=" + parent +  "]";
	}
	
}
