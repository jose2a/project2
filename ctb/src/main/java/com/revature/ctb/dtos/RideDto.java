package com.revature.ctb.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RideDto {

	protected Integer rideId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	protected Date departureDate;

	@JsonFormat(pattern = "HH:mm:ss")
	protected Date departureTime;

	protected int numberOfSeatsAvailable;

	protected double amountCharge;

	protected int numberOfBookings;

	protected EmployeeDto employee;

	protected CarDto car;

	protected RideStatusDto rideStatus;

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

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public CarDto getCar() {
		return car;
	}

	public void setCar(CarDto car) {
		this.car = car;
	}

	public RideStatusDto getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatusDto rideStatus) {
		this.rideStatus = rideStatus;
	}

}
