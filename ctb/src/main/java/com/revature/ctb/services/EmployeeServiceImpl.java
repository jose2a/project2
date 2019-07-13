package com.revature.ctb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.domains.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDAO employeeServ;

	@Override
	public boolean registerEmployee(Employee employee) {
		boolean result = employeeServ.addEmployee(employee);
		return result;
	}

}
