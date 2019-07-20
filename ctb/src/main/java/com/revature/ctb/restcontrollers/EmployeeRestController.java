package com.revature.ctb.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.dtos.LoginDTO;
import com.revature.ctb.services.EmployeeService;

@RestController
public class EmployeeRestController extends BasedRestController {

	private EmployeeService employeeServ;

	private final String PASSWORD_MASK = "**************";

	@Autowired
	public void setEmployeeServ(EmployeeService employeeServ) {
		this.employeeServ = employeeServ;
	}

	/**
	 * This method will register the employee in the system. This is going to be
	 * used by Angular UI.
	 * 
	 * @param employee Employee to register, JSON format object, coming from Angular
	 * 
	 * @return the employee with id following rest API practices
	 */
	@PostMapping(value = "/employee", consumes = "application/json") // access this using: localhost:8080/api/employee <-
																	// GET method
	@ResponseStatus(code = HttpStatus.CREATED)
	public Employee postEmployee(@Valid @RequestBody Employee employee) {

		// saving employee using the service
		employeeServ.registerEmployee(employee);

		// hiding employee password
		hidePassword(employee);

		return employee;
	}

	private void hidePassword(Employee employee) {
		employee.setPassword(PASSWORD_MASK);
	}

	@PutMapping(value = "employee/{employeeId}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee putEmployee(@PathVariable Integer employeeId, @Valid @RequestBody Employee employee) {
		// update employee
		employeeServ.updateEmployee(employee);

		hidePassword(employee);

		return employee;
	}

	@GetMapping(value = "employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee getEmployeeById(@PathVariable Integer employeeId) {

		Employee employee = employeeServ.getEmployeeById(employeeId);

		hidePassword(employee);

		return employee;
	}

	@GetMapping(value = "employee/username/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee getEmployeeByUsername(@PathVariable String username) {
		return employeeServ.getEmployeeByUsername(username);
	}

	@PostMapping(value = "employee/")
	@ResponseStatus(code = HttpStatus.OK)
	public Employee login(@RequestBody LoginDTO dto) {
		Employee employee = employeeServ.getEmployeeByUsernameAndPassword(dto.getUsername(), dto.getPassword());

		hidePassword(employee);

		return employee;
	}

	/**
	 * Get all the employees
	 * 
	 * @return The employees
	 */
	@GetMapping(value = "employee")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Employee> getAllEmployees() {
		List<Employee> employees = employeeServ.getAllEmployees();

		employees.forEach(emp -> hidePassword(emp));

		return employees;
	}
}
