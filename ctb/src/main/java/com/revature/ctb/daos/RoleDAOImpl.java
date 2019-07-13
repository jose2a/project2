package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Role;
import com.revature.ctb.utils.LogUtil;

@Repository
public class RoleDAOImpl implements RoleDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Role> getAllRoles() {
		// open hibernate session
		Session session = sessionFactory.openSession();

		// create a query
		Query<Role> theQuery = session.createQuery("from Role", Role.class);

		// execute query and get result list
		List<Role> roles = theQuery.getResultList();

		// close session
		session.close();

		// return the results
		return roles;
	}

	@Override
	public Role getRoleById(Integer roleId) {
		// open hibernate session
		Session session = sessionFactory.openSession();

		// execute query and get result list
		Role role = session.get(Role.class, roleId);
		role.getEmployees();
		
		LogUtil.debug(role.getEmployees().toString());

		// close session
		session.close();

		// return the results
		return role;
	}

}
