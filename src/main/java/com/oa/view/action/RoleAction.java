package com.oa.view.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Role;
import com.oa.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class RoleAction extends ActionSupport implements ModelDriven<Role>{
	private Role model = new Role();
//	private String name;
//	private String description;
	@Autowired
	private RoleService roleService;
	
	private static final long serialVersionUID = 1L;
	/**列表*/
	public String list() throws Exception{
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	/**删除*/
	public String delete() throws Exception{
		roleService.delete(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI() throws Exception{
		return "addUI";
	}
	/**添加*/
	public String add() throws Exception{
		roleService.save(model);
		return "toList";
	}
	/**修改页面*/
	public String editUI() throws Exception{
		model = roleService.getById(model.getId());
		return "editUI";
	}
	/**修改*/
	public String edit() throws Exception{
		roleService.update(model);
		return "toList";
	}
	@Override
	public Role getModel() {
		return this.model;
	}
	/*public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	
}
