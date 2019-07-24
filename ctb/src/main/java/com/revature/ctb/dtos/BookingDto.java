package com.revature.ctb.dtos;

import com.revature.ctb.domains.FeedbackType;

public class BookingDto {

	private Integer bookingId;

	private EmployeeDto employee;
	private RideDto ride;
	private RouteDto pickupLocation;
	private RouteDto destinationLocation;
	private FeedbackType passengerFeedback;
	private FeedbackType driverFeedback;

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public RideDto getRide() {
		return ride;
	}

	public void setRide(RideDto ride) {
		this.ride = ride;
	}

	public RouteDto getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(RouteDto pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public RouteDto getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(RouteDto destinationLocation) {
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

}
