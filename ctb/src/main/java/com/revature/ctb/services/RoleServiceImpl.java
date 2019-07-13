package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.RoleDAO;
import com.revature.ctb.domains.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDAO roleDao;

	@Override
	public List<Role> getAllRoles() {
		return roleDao.getAllRoles();
	}

	@Override
	public Role getRoleById(Integer roleId) {
		return roleDao.getRoleById(roleId);
	}

}
