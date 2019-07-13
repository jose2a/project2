package com.revature.ctb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.enums.RoleKeys;
import com.revature.ctb.exceptions.BadRequestException;
import com.revature.ctb.exceptions.DuplicateRecordException;
import com.revature.ctb.utils.LogUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;
	@Autowired
	private RoleService roleServ;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public boolean registerEmployee(Employee employee) {

		boolean employeeExist = employeeDao.getEmployeeByUsername(employee.getUsername()) != null;

		if (employeeExist) {
			LogUtil.trace("Employee with the same user already exist");
			throw new DuplicateRecordException("Employee with the same username has already registered.");
		}

		employee.setBlock(false); // Employee is not block
		employee.setNumberOfFlags(0); // Number of flags is 0

		if (employee.isDriver() && !employee.getDriverLicense().equals("xxxxxxxxxx")) {
			LogUtil.trace("This employee is a driver. Assign driver role to it");

			employee.addRole(roleServ.getRoleById(RoleKeys.Driver.getValue()));
		} else if (employee.isDriver() && employee.getDriverLicense().equals("xxxxxxxxxx")) {
			LogUtil.trace("This employee is a driver, but didn't provide a driver license number");

			BadRequestException brExc = new BadRequestException("Validation exception");
			brExc.addError("You need to provide a driver license");
			throw brExc;
		}

		employee.addRole(roleServ.getRoleById(RoleKeys.Passenger.getValue()));

		return employeeDao.addEmployee(employee);
	}

}
