package com.revature.ctb.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private Integer bookingId;

	// Many to one (many bookings for one employee)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "employee_id")
	private Employee employee;

	// Many to one (many bookings for one ride)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "ride_id")
	private Ride ride;

	// Many to one (many bookings for one pickup location)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "pickup_loc_id")
	private Route pickupLocation;

	// Many to one (many bookings for one destination)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "dest_loc_id")
	private Route destinationLocation;

	// Many to one (many bookings have one passenger feedback)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "passenger_feedback")
	private FeedbackType passengerFeedback;

	// Many to one (many bookings have one passenger feedback)
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "driver_feedback")
	private FeedbackType driverFeedback;

	public Booking() {
	}

	public Booking(Integer bookingId) {
		super();
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public Route getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(Route pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public Route getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(Route destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public FeedbackType getPassengerFeedback() {
		return passengerFeedback;
	}

	public void setPassengerFeedback(FeedbackType passengerFeedback) {
		this.passengerFeedback = passengerFeedback;
	}

	public FeedbackType getDriverFeedback() {
		return driverFeedback;
	}

	public void setDriverFeedback(FeedbackType driverFeedback) {
		this.driverFeedback = driverFeedback;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
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
		Booking other = (Booking) obj;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + "]";
	}

}
