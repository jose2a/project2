package com.revature.ctb.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
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

	@JsonIgnore // This will ignore to include the employees again when JSON is serializing
	@OneToMany(fetch = FetchType.LAZY) // Same like in employee, I couldn't insert in the employee_role table
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
	private List<Employee> employees = new ArrayList<>();

	public Role() {
		super();
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + "]";
	}

	public static class RoleIds {
		public static final Integer DRIVER = 1;
		public static final Integer PASSENGER = 2;
		public static final Integer ADMINISTRATOR = 3;
	}

}
