package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.RoleDAO;
import com.revature.ctb.domains.Role;
import com.revature.ctb.utils.LogUtil;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDao; // injecting roleDAO dependency

	/**
	 * Getting all the roles
	 */
	@Override
	public List<Role> getAllRoles() {
		LogUtil.trace("RoleServiceImpl - getAllRoles");

		return roleDao.getAllRoles();
	}

	/**
	 * Getting a role by id
	 */
	@Override
	public Role getRoleById(Integer roleId) {
		LogUtil.trace("RoleServiceImpl - getRoleById");

		return roleDao.getRoleById(roleId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateRole(Role role) {
		LogUtil.trace("RoleServiceImpl - updateRole");

		return roleDao.updateRole(role);
	}

}
