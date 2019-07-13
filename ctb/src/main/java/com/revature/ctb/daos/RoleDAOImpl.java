package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Role> getAllRoles() {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Role> theQuery = currentSession.createQuery("from Role", Role.class);

		// execute query and get result list
		List<Role> roles = theQuery.getResultList();

		// return the results
		return roles;
	}

}
