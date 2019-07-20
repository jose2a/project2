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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@Column(name = "employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;

	@Column(name = "username")
	@NotEmpty(message = "Username is required") // Validating null values
	private String username;

	@Column(name = "password")
	@NotEmpty(message = "Password is required") // Validating password
	private String password;

	@Column(name = "first_name")
	@NotEmpty(message = "First Name is required") // Validating
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "Last Name is required") // Validating
	private String lastName;

	@Column(name = "email")
	@NotEmpty(message = "Email is required") // Validating
	@Email(message = "Incorrect email format")
	private String email;

	@Column(name = "phone_number")
	@NotEmpty(message = "Phone Number is required") // Validating
	private String phoneNumber;

	@Column(name = "driver_license")
	private String driverLicense;

	@Column(name = "num_flags")
	private Integer numberOfFlags;

	@Column(name = "active")
	private boolean active;

	@Column(name = "block")
	private boolean blocked;

	@Column(name = "driver")
	private boolean driver;

	// Because the ManyToMany relationship wasn't working, I switched to a OneToMany
	// here
	// and use a ManyToOne in EmployeeRole class that way I keep the link between
	// Employee and Role
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_role", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles = new ArrayList<>();

	// One to many relationship (One employee can have many cars)
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "employee")
	private List<Car> cars = new ArrayList<>();

	// One to many relationship (One employee can have many requests for
	// information)
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_id")
	private List<InfoReq> infoRequests = new ArrayList<>();

	// One to many relationship (One employee can schedule many rides)
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Ride> rides = new ArrayList<>();

	@JsonIgnore
	// One to many relationship (One employee can book many rides)
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Booking> bookings = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String username, String password, String firstName, String lastName,
			String email, String phoneNumber, String driverLicense, Integer numberOfFlags, boolean active,
			boolean blocked, boolean driver) {
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
		this.blocked = blocked;
		this.driver = driver;
	}

	public Employee(String username, String password, String firstName, String lastName, String email,
			String phoneNumber, String driverLicense, Integer numberOfFlags, boolean active, boolean blocked,
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
		this.blocked = blocked;
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

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
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

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public List<InfoReq> getInfoRequests() {
		return infoRequests;
	}

	public void setInfoRequests(List<InfoReq> infoRequests) {
		this.infoRequests = infoRequests;
	}

	public List<Ride> getRides() {
		return rides;
	}

	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverLicense == null) ? 0 : driverLicense.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((numberOfFlags == null) ? 0 : numberOfFlags.hashCode());
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
		return "Employee [employeeId=" + employeeId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", driverLicense="
				+ driverLicense + ", numberOfFlags=" + numberOfFlags + ", active=" + active + ", blocked=" + blocked
				+ ", driver=" + driver + "]";
	}

}
