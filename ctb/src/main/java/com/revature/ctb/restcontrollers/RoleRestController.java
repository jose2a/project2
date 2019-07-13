package com.revature.ctb.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Role;
import com.revature.ctb.services.RoleService;

@RestController
@RequestMapping("/api")
public class RoleRestController {
	
	@Autowired
	private RoleService roleServ; // injecting roleService

	/**
	 * Getting roles from the rest controller.
	 * @return All roles
	 */
	@GetMapping("/roles")
	public List<Role> getAllRoles() {
		return roleServ.getAllRoles();
	}
}
