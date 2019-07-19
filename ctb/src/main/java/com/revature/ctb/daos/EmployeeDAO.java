package com.revature.ctb.daos;

import com.revature.ctb.domains.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	
	public boolean updateEmployee(Employee employee);
	
	public Employee getEmployeeById(Integer employeeId);
	
	public Employee getEmployeeByUsername(String username);

}
