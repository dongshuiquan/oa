package com.oa.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.oa.domain.Privilege;
import com.oa.service.PrivilegeService;

public class InitServletContextListener implements ServletContextListener {
    public InitServletContextListener() {
    }
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext application = sce.getServletContext();
    	ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application);
    	PrivilegeService privilegeService = ac.getBean(PrivilegeService.class);
    	//所有顶级权限的集合
    	List<Privilege> topPrivilegeList = privilegeService.findTopList();
    	application.setAttribute("topPrivilegeList", topPrivilegeList);
    	//所有顶级Url的集合
    	List<String> allPrivilegeUrls = privilegeService.findAllPrivilegeUrls();
    	application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
    }
	
}
