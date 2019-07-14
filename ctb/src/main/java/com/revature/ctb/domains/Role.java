package com.revature.ctb.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

	@Column(name = "name")
	private String name;

//	@JsonIgnore // This will ignore to include the employees again when JSON is serializing
//	@ManyToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
//			CascadeType.REFRESH })
//	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
//	private List<Employee> employees = new ArrayList<>();

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer roleId, String name) {
		super();
		this.roleId = roleId;
		this.name = name;
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<Employee> getEmployees() {
//		return employees;
//	}
//
//	public void setEmployees(List<Employee> employees) {
//		this.employees = employees;
//	}

	/**
	 * Convenience method to add an employee to this role
	 * 
	 * @param employee The employee
	 */
//	public void addEmployee(Employee employee) {
//		employees.add(employee);
//	}
//
//	/**
//	 * Convenience method to remove an employee from this role
//	 * 
//	 * @param emp The employee
//	 */
//	public void removeEmployee(Employee emp) {
//		employees.remove(emp);
//	}

//	@Override
//	public String toString() {
//		return "Role [roleId=" + roleId + ", name=" + name + ", employees=" + employees + "]";
//	}

}
