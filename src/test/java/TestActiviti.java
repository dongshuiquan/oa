import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class TestActiviti {
	@Autowired
	ProcessEngine processEngine;
	
	@Test
	public void start(){
		ProcessInstance pi = processEngine.getRuntimeService()
			.startProcessInstanceByKey("请假流程");
		System.out.println(pi.getId());
	}
}
