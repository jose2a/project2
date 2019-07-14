package com.revature.ctb.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.revature.ctb.config.CTBAppConfig;
import com.revature.ctb.domains.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CTBAppConfig.class })
@WebAppConfiguration
public class EmployeeServiceImplTest {
	
	@Autowired
	private EmployeeService empServ;
	private int id = 5;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testRegisterEmployee() {
		Employee emp = new Employee("peter.p" + id, "secret", "Peter", "Parker", "peter@ctb.com", "2334343", "A12132A", 0, true, false, true);
		
//		empServ.registerEmployee(emp);
	}

}
