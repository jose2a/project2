package com.revature.ctb.daos;

import com.revature.ctb.domains.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	
	public Employee getEmployeeByUsername(String username);

}
