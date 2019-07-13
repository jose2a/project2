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
	@NotNull(message = "Driver License is required") // Validating
	private String driverLicense;
	@Column(name = "num_flags")
	private Integer numberOfFlags;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "block")
	private Boolean block;
	@Column(name = "driver")
	private Boolean driver;

	// Mapping a m to m relationship
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer employeeId, String username, String password, String firstName, String lastName,
			String email, String phoneNumber, String driverLicense, Integer numberOfFlags, Boolean active,
			Boolean block, Boolean driver) {
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
			String phoneNumber, String driverLicense, Integer numberOfFlags, Boolean active, Boolean block,
			Boolean driver) {
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getBlock() {
		return block;
	}

	public void setBlock(Boolean block) {
		this.block = block;
	}

	public Boolean getDriver() {
		return driver;
	}

	public void setDriver(Boolean driver) {
		this.driver = driver;
	}

	public List<Role> getRoles() {
		// Checking if roles is null, we initialize it to avoid an exception
		if (roles == null) {
			roles = new ArrayList<>();
		}
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
		if (roles == null) {
			roles = new ArrayList<>();
		}

		roles.add(role);
	}

	/**
	 * Convenience method to remove a role from the user
	 * 
	 * @param role The role
	 */
	public void removeRole(Role role) {
		if (roles != null) {
			roles.remove(role);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((block == null) ? 0 : block.hashCode());
		result = prime * result + ((driver == null) ? 0 : driver.hashCode());
		result = prime * result + ((driverLicense == null) ? 0 : driverLicense.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((numberOfFlags == null) ? 0 : numberOfFlags.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Employee other = (Employee) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (block == null) {
			if (other.block != null)
				return false;
		} else if (!block.equals(other.block))
			return false;
		if (driver == null) {
			if (other.driver != null)
				return false;
		} else if (!driver.equals(other.driver))
			return false;
		if (driverLicense == null) {
			if (other.driverLicense != null)
				return false;
		} else if (!driverLicense.equals(other.driverLicense))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (numberOfFlags == null) {
			if (other.numberOfFlags != null)
				return false;
		} else if (!numberOfFlags.equals(other.numberOfFlags))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", driverLicense=" + driverLicense + ", numberOfFlags=" + numberOfFlags + ", active="
				+ active + ", block=" + block + ", driver=" + driver + "]";
	}

}
