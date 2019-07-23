package com.revature.ctb.services;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.exceptions.InputValidationException;

public interface EmployeeService {
	public boolean registerEmployee(Employee employee) throws InputValidationException;

	public boolean updateEmployee(Employee employee);

	public Employee getEmployeeById(Integer employeeId);

	public Employee getEmployeeByUsername(String username);

	public Employee getEmployeeByUsernameAndPassword(String username, String password);
}
