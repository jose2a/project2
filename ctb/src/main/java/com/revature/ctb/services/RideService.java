package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Ride;

public interface RideService {

	
	public void scheduleRide(Ride ride);
	
	public void cancelRide(Integer rideId);
	
	public void updateRide(Ride ride);

	public List<Ride> showAvailableRides();
	
	public List<Ride> showEmployeeRides(Integer employeeId);
	
	public Ride showRide(Integer rideId);
		
	public List<Ride> getAllRides();
	
}
