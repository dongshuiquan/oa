package com.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.oa.domain.Department;

public class DepartmentUtils {
	/**
	 * 遍历总站树， 得到所有部门列表， 并修改名称以表示层次
	 * @param topList
	 * @return
	 */
	public static List<Department> getAllDepartment(List<Department> topList) {
		List<Department> list = new ArrayList<>();
		walkDepartmentTree(topList,"-》", list);
		return list;
	}
	
	private static void walkDepartmentTree(Collection<Department> topList, String prefix, List<Department> resultList){
		/*Department copy = null;
		for(Department d : topList){
			copy = new Department();
			copy.setId(d.getId());
			copy.setName(prefix + d.getName());
			resultList.add(copy );
			walkDepartmentTree(d.getChildren(), "　" + prefix, resultList); //使用全角空格
		}*/
		for(Department d : topList){
			d.setName(prefix + d.getName()); 
			resultList.add(d);
			walkDepartmentTree(d.getChildren(), "　" + prefix, resultList); //使用全角空格
		}
	}

}
