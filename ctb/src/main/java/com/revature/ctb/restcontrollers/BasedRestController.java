package com.revature.ctb.restcontrollers;

import javax.servlet.http.HttpSession;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class BasedRestController {

	protected HttpSession session; // Handling the session
	protected DozerBeanMapper mapper; // Handling mapping between two objects
	
	public final static String EMPLOYEE_SESSION_KEY = "EMPLOYEE";

	@Autowired
	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Autowired
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

}
