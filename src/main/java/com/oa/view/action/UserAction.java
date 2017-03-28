package com.oa.view.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Department;
import com.oa.domain.PageBean;
import com.oa.domain.Role;
import com.oa.domain.User;
import com.oa.util.DepartmentUtils;
import com.oa.util.HqlHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	
	private static final long serialVersionUID = 1L;
	
	private Long departmentId;
	private Long[] roleIds;
	/**列表*/
	public String list() throws Exception{
//		List<User> userList = userService.findAll();
//		ActionContext.getContext().put("userList", userList);
		HqlHelper hqlHelper = new HqlHelper(User.class, "u");
		PageBean pageBean = userService.getPageBean(pageNum, hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "list";
	}
	/**删除*/
	public String delete() throws Exception{
		userService.delete(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI() throws Exception{
		//准备数据 ： departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext context = ActionContext.getContext();
		context.put("departmentList", departmentList);
		//准备数据 ： roleList
		List<Role> roleList = roleService.findAll();
		context.put("roleList", roleList);
		return "addUI";
	}
	/**添加*/
	public String add() throws Exception{
		Department department = departmentService.getById(departmentId);
		Set<Role> roles = roleService.getbyIds(roleIds);
		model.setDepartment(department);
		model.setRoles(roles);
		userService.save(model);
		return "toList";
	}
	/**修改页面*/
	public String editUI() throws Exception{
		//准备数据 ： departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext context = ActionContext.getContext();
		context.put("departmentList", departmentList);
		//准备数据 ： roleList
		List<Role> roleList = roleService.findAll();
		context.put("roleList", roleList);
		model = userService.getById(model.getId());
		Set<Role> roles= model.getRoles();
		List<Role> rolesList = new ArrayList<>(roles);
		roleIds = new Long[roles.size()];
		for(int i = 0; i < rolesList.size(); i++){
			roleIds[i] = rolesList.get(i).getId();
		}
		context.getValueStack().push(model);
		departmentId = model.getDepartment().getId();
		return "editUI";
	}
	/**修改*/
	public String edit() throws Exception{
		Department department = departmentService.getById(departmentId);
		Set<Role> roles = roleService.getbyIds(roleIds);
		model.setDepartment(department);
		model.setRoles(roles);
		userService.update(model);
		return "toList";
	}
	/**初始化密码为 1234 */
	public String initPassword() throws Exception{
		userService.initPassword(model.getId());
		return "toList";
	}
	
	/**登录页面*/
	public String loginUI() throws Exception{
		return "loginUI";
	}
	/**登录*/
	public String login() throws Exception{
		String username = model.getLoginName();
		String password = model.getPassword();
		User user = userService.checkByUsernameAndPassword(username, password);
		if(user != null){
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}else{
			addFieldError("login", "用户名或密码错误");
			return "loginUI";
		}
	}
	/**登出*/
	public String logout() throws Exception{
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
}
