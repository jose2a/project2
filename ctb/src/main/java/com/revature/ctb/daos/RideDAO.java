package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Ride;

public interface RideDAO {
	
	public boolean addRide(Ride ride);
	
	public boolean updateRide(Ride ride);
	
	public boolean deleteRide(Integer rideId);
	
	public List<Ride> getRidesByEmpId(Integer rideId);
	
	public List<Ride> getEmployeeActiveRides(Integer employeeId);
	
	public List<Ride> getAllActiveRides();
	
	public List<Ride> getAllRides();
	
	
}
