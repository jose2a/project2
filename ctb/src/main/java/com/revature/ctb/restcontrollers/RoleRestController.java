package com.revature.ctb.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Role;
import com.revature.ctb.services.RoleService;

@RestController
public class RoleRestController extends BasedRestController {

	private RoleService roleServ;

	@Autowired
	public void setRoleServ(RoleService roleServ) {
		this.roleServ = roleServ;
	}

	/**
	 * Getting roles from the rest controller.
	 * 
	 * @return All roles
	 */
	@GetMapping("/roles")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Role> getAllRoles() {
		return roleServ.getAllRoles();
	}

	/**
	 * Getting roles for this employee
	 * 
	 * @param employeeId The employee id
	 * @return List of roles
	 */
	@GetMapping("/employee/{employeeId}/roles")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Role> getRolesForEmployee(@PathVariable Integer employeeId) {
		return roleServ.getRolesForEmployee(employeeId);
	}
}
