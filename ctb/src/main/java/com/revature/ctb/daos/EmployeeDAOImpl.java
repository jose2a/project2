package com.revature.ctb.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.utils.LogUtil;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();

		LogUtil.debug(">>>>>>>>>>> Roles: " + employee.getRoles().toString());

		// save employee
		session.save(employee);

		// if employeeId is greater than 0, it means the employee was inserted
		return employee.getEmployeeId() > 0;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();

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

	@Override
	public boolean updateEmployee(Employee employee) {
		Session session = sessionFactory.getCurrentSession();

		// save employee
		session.saveOrUpdate(employee);

		// if employeeId is greater than 0, it means the employee was inserted
		return true;
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Session session = sessionFactory.getCurrentSession();

		// get employee
		return session.get(Employee.class, employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from Employee", Employee.class).getResultList();
	}

	@Override
	public List<Employee> getBlockedEmployees() {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from Employee where blocked = true", Employee.class).getResultList();
	}

}
