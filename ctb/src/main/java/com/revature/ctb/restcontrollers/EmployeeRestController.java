package com.revature.ctb.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.exceptions.BadRequestException;
import com.revature.ctb.services.EmployeeService;
import com.revature.ctb.utils.ValidationUtil;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeServ; // injecting employeeService

	@PostMapping("employee") // access this using: localhost:8080/api/employee <- GET method
	@ResponseStatus(code = HttpStatus.OK)
	public Employee postEmployee(@Valid @RequestBody Employee emp, BindingResult theBindingResult)
			throws BadRequestException {

		// This method validates the entity
		ValidationUtil.checkModelForValidationErrors(theBindingResult);

		// saving employee using the service
		employeeServ.registerEmployee(emp);

		return emp;
	}
}
