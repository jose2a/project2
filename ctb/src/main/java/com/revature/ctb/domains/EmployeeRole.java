package com.revature.ctb.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This domain class, keeps the relationship between Employees and Roles It was
 * needed because using a Many to Many relationship was not working Its main use
 * is only for assigning the roles to the employee
 *
 */
@Entity
@Table(name = "employee_role")
public class EmployeeRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employeerole_id")
	private Integer employeeroleId; // Id

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee; // Employee

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role; // Role

	public EmployeeRole() {
	}

	public EmployeeRole(Integer employeeroleId, Employee employee, Role role) {
		super();
		this.employeeroleId = employeeroleId;
		this.employee = employee;
		this.role = role;
	}

	public EmployeeRole(Employee employee, Role role) {
		super();
		this.employee = employee;
		this.role = role;
	}

	public Integer getEmployeeroleId() {
		return employeeroleId;
	}

	public void setEmployeeroleId(Integer employeeroleId) {
		this.employeeroleId = employeeroleId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((employeeroleId == null) ? 0 : employeeroleId.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeRole other = (EmployeeRole) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (employeeroleId == null) {
			if (other.employeeroleId != null)
				return false;
		} else if (!employeeroleId.equals(other.employeeroleId))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeeRole [employee=" + employee + ", role=" + role + "]";
	}

}
