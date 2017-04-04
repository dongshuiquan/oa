package com.oa.view.action;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Privilege;
import com.oa.domain.Role;
import com.oa.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class RoleAction extends ModelDrivenBaseAction<Role>{
	@Autowired
	private RoleService roleService;
	
	private Long[] privilegeIds;
	
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
	/**设置权限页面*/
	public String setPrivilegeUI() throws Exception{
		 //准备数据
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().put("role", role);
		 
		List<Privilege> privilegeList = null;
		privilegeList = privilegeService.findTopList();
		ActionContext.getContext().put("topPrivilegeList", privilegeList);
		privilegeIds = new Long[role.getPrivileges().size()];
		int index = 0;
		for(Privilege privilege : role.getPrivileges()){
			privilegeIds[index++] = privilege.getId();
		}
		return "setPrivilegeUI";
	}
	/**设置权限*/
	public String setPrivilege() throws Exception{
		//从数据库中取出原对角
		Role role = roleService.getById(model.getId());
		//设置要修改的属性
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		roleService.update(role);
		return "toList";
	}
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
