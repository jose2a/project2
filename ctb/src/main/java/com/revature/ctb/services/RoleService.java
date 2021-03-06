package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Role;

public interface RoleService {
	public List<Role> getAllRoles();
	
	public Role getRoleById(Integer roleId);
	
	public void addRolesToEmployee(Employee employee, List<Role> roles);

	List<Role> getRolesForEmployee(Integer employeeId);
}
