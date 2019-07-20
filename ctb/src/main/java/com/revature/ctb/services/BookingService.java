package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Booking;

public interface BookingService {
	
	public void addBooking(Booking booking);
	
	public void deleteOneBooking(Integer employeeId);
	
	public void deleteAllBooking(Integer rideId);
	
//	public void updateBooking(Booking booking);
	
	public List<Booking> getAllBookingByRideId(Integer rideId);
	
	public List<Booking> getAllBooking();
}
