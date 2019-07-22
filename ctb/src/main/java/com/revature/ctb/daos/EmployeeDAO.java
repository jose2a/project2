package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	
	public boolean updateEmployee(Employee employee);
	
	public Employee getEmployeeById(Integer employeeId);
	
	public Employee getEmployeeByUsername(String username);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getBlockedEmployees();

}
