package com.revature.ctb.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addEmployee(Employee employee) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save employee
		currentSession.save(employee);

		// if employeeId is greater than 0 it means the employee was inserted
		return employee.getEmployeeId() > 0;
	}

}
