package com.revature.ctb.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ride")
public class Ride {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ride_id")
	private Integer rideId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "departure_date")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Departure date is required") // Validating
	private Date departureDate;

	@JsonFormat(pattern = "HH:mm:ss")
	@Column(name = "departure_time")
	@Temporal(TemporalType.TIME)
	@NotNull(message = "Departure time is required") // Validating
	private Date departureTime;

	@Column(name = "num_seats_available")
	@Min(value = 1, message = "Number of seats must be greater than zero")
	private int numberOfSeatsAvailable;

	@Column(name = "amount_charge")
	private double amountCharge;

	@Column(name = "num_bookings")
	private int numberOfBookings;

	// Many to one (many rides for one car)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "employee_id")
	private Employee employee;

	// Many to one (many rides for one employee)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "car_id")
	private Car car;

	// Many to one (many rides can have one status at the time)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "ridestatus_id")
	private RideStatus rideStatus;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ride")
	private List<Route> routes = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "ride")
	private List<Booking> bookings = new ArrayList<>();

	public Ride() {
	}

	public Ride(Date departureDate, Date departureTime, int numberOfSeatsAvailable, double amountCharge,
			int numberOfBookings, Employee employee, Car car, RideStatus rideStatus) {
		super();
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
		this.amountCharge = amountCharge;
		this.numberOfBookings = numberOfBookings;
		this.employee = employee;
		this.car = car;
		this.rideStatus = rideStatus;
	}

	public Ride(Integer rideId, Date departureDate, Date departureTime, int numberOfSeatsAvailable, double amountCharge,
			int numberOfBookings, Employee employee, Car car, RideStatus rideStatus) {
		super();
		this.rideId = rideId;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
		this.amountCharge = amountCharge;
		this.numberOfBookings = numberOfBookings;
		this.employee = employee;
		this.car = car;
		this.rideStatus = rideStatus;
	}

	public Integer getRideId() {
		return rideId;
	}

	public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public int getNumberOfSeatsAvailable() {
		return numberOfSeatsAvailable;
	}

	public void setNumberOfSeatsAvailable(int numberOfSeatsAvailable) {
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
	}

	public double getAmountCharge() {
		return amountCharge;
	}

	public void setAmountCharge(double amountCharge) {
		this.amountCharge = amountCharge;
	}

	public int getNumberOfBookings() {
		return numberOfBookings;
	}

	public void setNumberOfBookings(int numberOfBookings) {
		this.numberOfBookings = numberOfBookings;
	}

	public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public List<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(List<Route> routes) {
		this.routes = routes;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountCharge);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + numberOfSeatsAvailable;
		result = prime * result + ((rideId == null) ? 0 : rideId.hashCode());
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
		Ride other = (Ride) obj;
		if (Double.doubleToLongBits(amountCharge) != Double.doubleToLongBits(other.amountCharge))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (numberOfSeatsAvailable != other.numberOfSeatsAvailable)
			return false;
		if (rideId == null) {
			if (other.rideId != null)
				return false;
		} else if (!rideId.equals(other.rideId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ride [rideId=" + rideId + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", numberOfSeatsAvailable=" + numberOfSeatsAvailable + ", amountCharge=" + amountCharge
				+ ", numberOfBookings=" + numberOfBookings + ", employee=" + employee + ", car=" + car + ", rideStatus="
				+ rideStatus + "]";
	}

}
