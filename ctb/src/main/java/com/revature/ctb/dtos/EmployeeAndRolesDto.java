package com.revature.ctb.dtos;

import java.util.List;

public class EmployeeAndRolesDto extends EmployeeDto {

	private List<RoleDto> roles;

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

}
