package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.RoleDAO;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Role;
import com.revature.ctb.utils.LogUtil;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleDAO roleDao;

	@Autowired
	public RoleServiceImpl(RoleDAO roleDao) {
		super();
		this.roleDao = roleDao;
	}

	/**
	 * Getting all the roles
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Role> getAllRoles() {
		LogUtil.trace("RoleServiceImpl - getAllRoles");

		return roleDao.getAllRoles();
	}

	/**
	 * Getting a role by id
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Role getRoleById(Integer roleId) {
		LogUtil.debug(">>>>>>RoleServiceImpl - getRoleById");

		return roleDao.getRoleById(roleId);
	}

	/**
	 * Adding roles to employee
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRolesToEmployee(Employee employee, List<Role> roles) {
		roleDao.addRolesToEmployee(employee, roles);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Role> getRolesForEmployee(Integer employeeId) {
		LogUtil.trace("RoleServiceImpl - getRolesForEmployee");

		return roleDao.getRolesForEmployee(employeeId);

	}

}
