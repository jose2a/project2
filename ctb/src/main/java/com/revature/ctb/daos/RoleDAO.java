package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Role;

public interface RoleDAO {
	public List<Role> getAllRoles();
	
	public Role getRoleById(Integer roleId);
	
	public boolean updateRole(Role role);

}
