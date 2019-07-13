package com.revature.ctb.services;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.exceptions.BadRequestException;

public interface EmployeeService {
	public boolean registerEmployee(Employee employee) throws BadRequestException;
}
