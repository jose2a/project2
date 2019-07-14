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

		// open hibernate session, spring closes when we use the @Transactional annotation in the service
		Session session = sessionFactory.openSession();

		LogUtil.debug(">>>>>>>>>>> Roles: " + employee.getRoles().toString());

		// save employee
		session.save(employee);

		// if employeeId is greater than 0, it means the employee was inserted
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
			// the query return something, it means an employee with same username was found
			employee = (Employee) query.getSingleResult();
		}

		return employee;
	}

}
