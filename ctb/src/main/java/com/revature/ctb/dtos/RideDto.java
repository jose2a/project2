package com.revature.ctb.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RideDto {

	private Integer rideId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date departureDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private Date departureTime;

	private int numberOfSeatsAvailable;

	private double amountCharge;

	private int numberOfBookings;

	private EmployeeDto employee;

	private CarDto car;

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

}
