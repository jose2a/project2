package com.revature.ctb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Role;
import com.revature.ctb.exceptions.DuplicateRecordException;
import com.revature.ctb.exceptions.InputValidationException;
import com.revature.ctb.utils.LogUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDao; // injecting employeeDAO
	private RoleService roleServ; // injecting roleService

	@Autowired
	public void setEmployeeDao(EmployeeDAO employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Autowired
	public void setRoleServ(RoleService roleServ) {
		this.roleServ = roleServ;
	}

	@Override
	public boolean registerEmployee(Employee employee) {

		boolean employeeExist = employeeDao.getEmployeeByUsername(employee.getUsername()) != null;

		if (employeeExist) {
			LogUtil.debug(">>>>>>>>>> Employee with the same user already exist");

			DuplicateRecordException drExc = new DuplicateRecordException();
			drExc.addError("Employee with the same username has already registered.");

			throw drExc;
		}

		employee.setBlocked(false); // Employee is not block
		employee.setNumberOfFlags(0); // Number of flags is 0

		List<Role> roles = new ArrayList<>();

		// Validating if employee is a driver, the employee should also provide the
		// driver license
		if (employee.isDriver()
				&& (employee.getDriverLicense() != null && !employee.getDriverLicense().trim().isEmpty())) {

			LogUtil.trace("This employee is a driver. Assign driver role to it");

			Role driver = roleServ.getRoleById(Role.RoleIds.DRIVER);
			roles.add(driver);
			employee.getRoles().add(driver);

		} else if (employee.isDriver()
				&& (employee.getDriverLicense() == null || employee.getDriverLicense().trim().isEmpty())) {

			LogUtil.trace("This employee is a driver, but didn't provide a driver license number");

			InputValidationException brExc = new InputValidationException("Validation exception");
			brExc.addError("You need to provide a driver license as a driver.");
			throw brExc;
		}

		boolean added = employeeDao.addEmployee(employee);

		Role passenger = roleServ.getRoleById(Role.RoleIds.PASSENGER);
		roles.add(passenger);
		employee.getRoles().add(passenger);

		// if the employee was successfully inserted in the database we can insert its
		// roles
		if (added) {
			roleServ.addRolesToEmployee(employee, roles);
		}

		return added;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		Employee oldEmployee = getEmployeeById(employee.getEmployeeId());

		employee.setPassword(oldEmployee.getPassword());
		employee.setRoles(oldEmployee.getRoles());

		return employeeDao.updateEmployee(employee);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) {
		Employee employee = employeeDao.getEmployeeById(employeeId);
		employee.getRoles(); // Loading the roles for the employee

		return employee;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		Employee employee = employeeDao.getEmployeeByUsername(username);
		employee.getRoles(); // Loading the roles for the employee

		return employee;
	}

	@Override
	public Employee getEmployeeByUsernameAndPassword(String username, String password) {
		Employee employee = employeeDao.getEmployeeByUsername(username);

		if (employee.getPassword().equals(password)) {
			employee.getRoles(); // Loading the roles for the employee

			return employee;
		}

		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDao.getAllEmployees();
	}

}
