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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "username")
	@NotNull(message = "Username is required") // Validating null values
	private String username;

	@Column(name = "password")
	@NotNull(message = "Password is required") // Validating password
	private String password;

	@Column(name = "first_name")
	@NotNull(message = "First Name is required") // Validating
	private String firstName;

	@Column(name = "last_name")
	@NotNull(message = "Last Name is required") // Validating
	private String lastName;

	@Column(name = "email")
	@NotNull(message = "Email is required") // Validating
	@Email(message = "Incorrect email format")
	private String email;

	@Column(name = "phone_number")
	@NotNull(message = "Phone Number is required") // Validating
	private String phoneNumber;

	@Column(name = "driver_license")
	private String driverLicense;

	@Column(name = "num_flags")
	private Integer numberOfFlags;

	@Column(name = "active")
	private boolean active;

	@Column(name = "block")
	private boolean block;

	@Column(name = "driver")
	private boolean driver;

	// Mapping a m to m relationship
	@ManyToMany(fetch = FetchType.LAZY,  cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//	@ManyToMany(mappedBy = "employees")
	private List<Role> roles = new ArrayList<>();

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer employeeId, String username, String password, String firstName, String lastName,
			String email, String phoneNumber, String driverLicense, Integer numberOfFlags, boolean active,
			boolean block, boolean driver) {
		super();
		this.employeeId = employeeId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driverLicense = driverLicense;
		this.numberOfFlags = numberOfFlags;
		this.active = active;
		this.block = block;
		this.driver = driver;
	}

	public Employee(String username, String password, String firstName, String lastName, String email,
			String phoneNumber, String driverLicense, Integer numberOfFlags, boolean active, boolean block,
			boolean driver) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.driverLicense = driverLicense;
		this.numberOfFlags = numberOfFlags;
		this.active = active;
		this.block = block;
		this.driver = driver;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public Integer getNumberOfFlags() {
		return numberOfFlags;
	}

	public void setNumberOfFlags(Integer numberOfFlags) {
		this.numberOfFlags = numberOfFlags;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isBlock() {
		return block;
	}

	public void setBlock(boolean block) {
		this.block = block;
	}

	public boolean isDriver() {
		return driver;
	}

	public void setDriver(boolean driver) {
		this.driver = driver;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * Convenience method to add a new role to the user
	 * 
	 * @param role The role
	 */
	public void addRole(Role role) {
		roles.add(role);
	}

	/**
	 * Convenience method to remove a role from the user
	 * 
	 * @param role The role
	 */
	public void removeRole(Role role) {
		roles.remove(role);
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", driverLicense=" + driverLicense + ", numberOfFlags=" + numberOfFlags + ", active="
				+ active + ", block=" + block + ", driver=" + driver + "]";
	}

}
