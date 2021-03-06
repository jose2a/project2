package com.revature.ctb.daos;

import java.text.ParseException;
import java.util.List;

import com.revature.ctb.domains.Ride;

public interface RideDAO {
	
	public boolean addRide(Ride ride);
	
	public boolean updateRide(Ride ride);
	
	public boolean deleteRide(Integer rideId);
	
	public List<Ride> getRidesByEmpId(Integer employeeId);
	
	public List<Ride> getEmployeeActiveRides(Integer employeeId) throws ParseException;
	
	public List<Ride> getAllActiveRides() throws ParseException;
	
	public List<Ride> getAllRides();
	
	public Ride getRidebyId(Integer rideId);
	
	
}
