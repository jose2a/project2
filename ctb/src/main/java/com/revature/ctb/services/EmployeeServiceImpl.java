package com.revature.ctb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Role;
import com.revature.ctb.enums.RoleKeys;
import com.revature.ctb.exceptions.BadRequestException;
import com.revature.ctb.exceptions.DuplicateRecordException;
import com.revature.ctb.utils.LogUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao; // injecting employeeDAO
	@Autowired
	private RoleService roleServ; // injecting roleService

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public boolean registerEmployee(Employee employee) {

		boolean employeeExist = employeeDao.getEmployeeByUsername(employee.getUsername()) != null;

		if (employeeExist) {
			LogUtil.trace("Employee with the same user already exist");
			throw new DuplicateRecordException("Employee with the same username has already registered.");
		}

		employee.setBlock(false); // Employee is not block
		employee.setNumberOfFlags(0); // Number of flags is 0
		
		List<Role> roles = new ArrayList<>();

		// Validating if employee is a driver, the employee should also provide the
		// driver license
		if (employee.isDriver()
				&& (employee.getDriverLicense() != null && !employee.getDriverLicense().trim().isEmpty())) {
			LogUtil.trace("This employee is a driver. Assign driver role to it");
			
			Role driver = roleServ.getRoleById(RoleKeys.Driver.getValue());
			roles.add(driver);
//			employee.getRoles().add(driver);
//			driver.getEmployees().add(employee);
		} else if (employee.isDriver()
				&& (employee.getDriverLicense() == null || employee.getDriverLicense().trim().isEmpty())) {
			LogUtil.trace("This employee is a driver, but didn't provide a driver license number");

			BadRequestException brExc = new BadRequestException("Validation exception");
			brExc.addError("You need to provide a driver license as a driver.");
			throw brExc;
		}

		Role passenger = roleServ.getRoleById(RoleKeys.Passenger.getValue());
		roles.add(passenger);
//		employee.getRoles().add(passenger);
//		passenger.getEmployees().add(employee);

		boolean added = employeeDao.addEmployee(employee);
		
//		roleServ.updateRole(driver);
		roleServ.addRolesToEmployee(employee, roles);

		return added;
	}

}
