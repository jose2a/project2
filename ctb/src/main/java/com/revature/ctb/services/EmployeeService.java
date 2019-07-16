package com.revature.ctb.services;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.exceptions.InputValidationException;

public interface EmployeeService {
	public boolean registerEmployee(Employee employee) throws InputValidationException;
}
