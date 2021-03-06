package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.EmployeeRole;
import com.revature.ctb.domains.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {

	// need to inject the session factory
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Role> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();

		// create a query
		Query<Role> theQuery = session.createQuery("from Role", Role.class);

		// execute query and get result list
		List<Role> roles = theQuery.getResultList();

		// return the results
		return roles;
	}

	@Override
	public Role getRoleById(Integer roleId) {
		Session session = sessionFactory.getCurrentSession();

		// execute query and get result list
		Role role = session.get(Role.class, roleId);

		// return the results
		return role;
	}

	@Override
	public void addRolesToEmployee(Employee employee, List<Role> roles) {
		Session session = sessionFactory.getCurrentSession();

		// I had to use and intermediate class (EmployeeRole), because ManyToMany
		// relationship was not working
		for (Role role : roles) {
			EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setEmployee(employee); // Linking employee
			employeeRole.setRole(role); // Linking role

			session.save(employeeRole); // Saving role for employee
		}
	}

	@Override
	public List<Role> getRolesForEmployee(Integer employeeId) {
		Session session = sessionFactory.getCurrentSession();

		Employee employee = session.get(Employee.class, employeeId); // Getting employee to get its roles

		return employee.getRoles();
	}

}
