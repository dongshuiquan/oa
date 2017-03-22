package com.oa.view.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Department;
import com.oa.service.DepartmentService;
import com.oa.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department>{
	private Long parentId;
	private Department model = new Department();
	@Autowired
	private DepartmentService departmentService;
	
	private static final long serialVersionUID = 1L;
	/**列表*/
	public String list() throws Exception{
		List<Department> departmentList;
		if(parentId == null){
			departmentList = departmentService.findTopList();
		}else{
			departmentList = departmentService.findChildren(parentId);
			Department parent = departmentService.getById(parentId);
			ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	/**删除*/
	public String delete() throws Exception{
		departmentService.delete(model.getId());
		return "toList";
	}
	/**添加页面*/
	public String addUI() throws Exception{
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		return "addUI";
	}
	/**添加*/
	public String add() throws Exception{
		if(parentId != null)
			model.setParent(departmentService.getById(parentId));
		departmentService.save(model);
		return "toList";
	}
	/**修改页面*/
	public String editUI() throws Exception{
		//准备数据， departmentList
		List<Department> topList = departmentService.findTopList();
		List<Department> departmentList = DepartmentUtils.getAllDepartment(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		//返回回显的信息
		Department department = departmentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent() != null){
			parentId = department.getParent().getId();
		}
		return "editUI";
	}
	/**修改*/
	public String edit() throws Exception{
		if(parentId != null){
			model.setParent(departmentService.getById(parentId));
		}
		departmentService.update(model);
		return "toList";
	}
	@Override
	public Department getModel() {
		return this.model;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
}
