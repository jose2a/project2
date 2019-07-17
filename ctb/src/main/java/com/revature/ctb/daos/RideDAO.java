package com.revature.ctb.daos;

import java.util.Date;
import java.util.List;

import com.revature.ctb.domains.Ride;

public interface RideDAO {
	
	public boolean addRide(Ride ride);
	
	public boolean updateRide(Ride ride);
	
	public boolean deleteRide(Integer rideId);
	
	public List<Ride> getRidesByEmpId(Integer rideId);
	
	public List<Ride> getEmployeeRideByDate(Integer employeeId, Date departureDate);
	
	public List<Ride> getRideByDate(Date departureDate);
	
	public List<Ride> getAllRides();
	
	
}
