package com.oa.view.action;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oa.view.action.TestAction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-service.xml")
public class TestActionTest {
	@Autowired
	TestAction action;
	
	@Test
	public void testExist(){
		System.out.println(action);
	}
}
