package com.revature.ctb.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.enums.RoleKeys;
import com.revature.ctb.exceptions.DuplicateRecordException;
import com.revature.ctb.utils.LogUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;
	@Autowired
	private RoleService roleServ;

	@Override
	@Transactional
	public boolean registerEmployee(Employee employee) {

		boolean employeeExist = employeeDao.getEmployeeByUsername(employee.getUsername()) != null;

		if (employeeExist) {
			LogUtil.trace("Employee with the same user already exist");
			throw new DuplicateRecordException("Employee with the same username has already registered.");
		}

		if (employee.getDriver()) {
			employee.addRole(roleServ.getRoleById(RoleKeys.Driver.getValue()));
		}

		employee.addRole(roleServ.getRoleById(RoleKeys.Passenger.getValue()));

		return employeeDao.addEmployee(employee);
	}

}
