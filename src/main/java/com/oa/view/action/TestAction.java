package com.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 整合测试用
 * @author dong
 *
 */
@Component("testAction")
@Scope("prototype")
public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
}
