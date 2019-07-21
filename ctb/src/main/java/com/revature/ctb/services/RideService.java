package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;

public interface RideService {

	public void scheduleRide(Ride ride);

	public void updateRide(Ride ride);

	public void cancelRide(Integer rideId);

	public List<Ride> showAvailableRides();

	public List<Ride> showEmployeeRides(Integer employeeId);

	public Ride showRide(Integer rideId);

	public List<Ride> getAllRides();

	public void sendMessageToPassengers(List<Booking> bookings, String message);

	public void driverMessageToPassengers(Integer rideId, String message);

}
