package com.revature.ctb.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.dtos.EmployeeAndRolesDto;
import com.revature.ctb.dtos.EmployeeDto;
import com.revature.ctb.dtos.LoginDto;
import com.revature.ctb.services.EmployeeService;
import com.revature.ctb.services.RoleService;

@RestController
public class EmployeeRestController extends BasedRestController {

	private EmployeeService employeeServ;
	private RoleService roleServ;

	@Autowired
	public void setEmployeeServ(EmployeeService employeeServ) {
		this.employeeServ = employeeServ;
	}

	@Autowired
	public void setRoleServ(RoleService roleServ) {
		this.roleServ = roleServ;
	}

	/**
	 * This method will register the employee in the system. This is going to be
	 * used by Angular UI.
	 * 
	 * @param employee Employee to register, JSON format object, coming from Angular
	 * 
	 * @return the employee with id following rest API practices
	 */
	@PostMapping(value = "/employee", consumes = "application/json") // access this using: localhost:8080/api/employee
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeDto postEmployee(@Valid @RequestBody Employee employee) {

		// saving employee using the service
		employeeServ.registerEmployee(employee);

		return mapper.map(employee, EmployeeDto.class);
	}

	/**
	 * Update employee.
	 * 
	 * @param employeeId Employee id
	 * @param employee   Employee with updated information
	 * @return Updated employee
	 */
	@PutMapping(value = "employee/{employeeId}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeDto putEmployee(@PathVariable Integer employeeId, @Valid @RequestBody Employee employee) {
		// update employee
		employeeServ.updateEmployee(employee);

		return mapper.map(employee, EmployeeDto.class);
	}

	/**
	 * Get employee by id
	 * 
	 * @param employeeId employee id
	 * @return employee
	 */
	@GetMapping(value = "employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeDto getEmployeeById(@PathVariable Integer employeeId) {

		Employee employee = employeeServ.getEmployeeById(employeeId);

		return mapper.map(employee, EmployeeDto.class);
	}

	/**
	 * Get employee by username
	 * 
	 * @param username username
	 * @return the employee
	 */
	@GetMapping(value = "employee/username/{username}")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeDto getEmployeeByUsername(@PathVariable String username) {
		Employee employee = employeeServ.getEmployeeByUsername(username);

		return mapper.map(employee, EmployeeDto.class);
	}

	/**
	 * Login employee in the database
	 * 
	 * @param dto Login dto
	 * @return employee
	 */
	@PostMapping(value = "employee/login")
	@ResponseStatus(code = HttpStatus.OK)
	public EmployeeAndRolesDto login(@RequestBody LoginDto dto) {
		Employee employee = employeeServ.getEmployeeByUsernameAndPassword(dto.getUsername(), dto.getPassword());
		employee.setRoles(roleServ.getRolesForEmployee(employee.getEmployeeId()));

		if (employee != null) {
			session.setAttribute(EMPLOYEE_SESSION_KEY, employee);
		}

		return mapper.map(employee, EmployeeAndRolesDto.class);
	}

	/**
	 * Get all the employees
	 * 
	 * @return The employees
	 */
	@GetMapping(value = "employee")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();

		employeeServ.getAllEmployees().forEach(emp -> employees.add(mapper.map(emp, EmployeeDto.class)));

		return employees;
	}

	/**
	 * Get all the employees
	 * 
	 * @return The employees
	 */
	@GetMapping(value = "employee/blocked")
	@ResponseStatus(code = HttpStatus.OK)
	public List<EmployeeDto> getBlockedEmployees() {
		List<EmployeeDto> employees = new ArrayList<EmployeeDto>();

		employeeServ.getBlockedEmployees().forEach(emp -> employees.add(mapper.map(emp, EmployeeDto.class)));

		return employees;
	}

	/**
	 * We can unblock employee after the system blocked it
	 * 
	 * @param employeeId employee id
	 */
	@PutMapping(value = "employee/{employeeId}/unblock")
	@ResponseStatus(code = HttpStatus.OK)
	public void unBlockedEmployee(@PathVariable Integer employeeId) {

		employeeServ.unBlockedEmployee(employeeId);
	}

	/**
	 * Soft delete the employee
	 * 
	 * @param employeeId employee id
	 */
	@DeleteMapping(value = "employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteEmployee(@PathVariable Integer employeeId) {

		employeeServ.deleteEmployee(employeeId);
	}

	@GetMapping(value = "employee/logout")
	@ResponseStatus(code = HttpStatus.OK)
	public void logoutEmployee() {
		if (session != null && session.getAttribute(EMPLOYEE_SESSION_KEY) != null) {
			session.invalidate();
		}
	}
}
