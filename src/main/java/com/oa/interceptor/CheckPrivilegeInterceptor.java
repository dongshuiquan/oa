package com.oa.interceptor;

import com.oa.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckPrivilegeInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取当前用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		
		//获取当前访问的 URL, 并去掉当前应用程序的前缀(就是 : actionName)
		String privilegeUrl = null;
		String nameSpace = invocation.getProxy().getNamespace();
		String actionName = invocation.getProxy().getActionName();
		if(nameSpace.endsWith("/")){
			privilegeUrl = nameSpace + actionName;
		}else{
			privilegeUrl = nameSpace + "/" + actionName;
		}
		//去掉开关 /
		if(privilegeUrl.startsWith("/")){
			privilegeUrl = privilegeUrl.substring(1);
		}
		//如果未登录用户
		if(user == null){
			if(!actionName.startsWith("userAction_login")){ //userAction_login , userAction_loginUI
				return "loginUI";
			}else{
				return invocation.invoke();
			}
		}else{
			if(user.hasPrivilegeByUrl(privilegeUrl)){
				return invocation.invoke();
			}else{
				return "noPrivilegeError";
			}
		}
	}

}
