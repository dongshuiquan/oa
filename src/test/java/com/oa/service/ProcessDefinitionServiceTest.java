package com.oa.service;


import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class ProcessDefinitionServiceTest {

	@Autowired
	ProcessDefinitionService processDefinitionService; 
	@Test
	public void testgetResourceImageResourceAsStream() {
		InputStream in = processDefinitionService.getResourceImageResourceAsStream("1");
		System.out.println(in);
	}

}
