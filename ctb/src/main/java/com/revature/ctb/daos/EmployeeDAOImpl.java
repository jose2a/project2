package com.revature.ctb.daos;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.utils.LogUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addEmployee(Employee employee) {
		LogUtil.trace("EmployeeDAOImpl - Add Employee");
		
		// open hibernate session
		Session session = sessionFactory.openSession();

		// save employee
		session.save(employee);

		// close session
		session.close();

		// if employeeId is greater than 0 it means the employee was inserted
		return employee.getEmployeeId() > 0;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		LogUtil.trace("EmployeeDAOImpl - Get Employee by username");

		// open hibernate session
		Session session = sessionFactory.openSession();

		// get employee
		Query query = session.createQuery("from Employee where username = :username");
		query.setParameter("username", username);

		Employee employee = null;

		if (!query.getResultList().isEmpty()) {
			// the query return something, so it means an employee with same username was found
			employee = (Employee) query.getSingleResult();
		}

		// close session
		session.close();

		return employee;
	}

}