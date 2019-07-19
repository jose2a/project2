package com.revature.ctb.services;

import java.util.Date;
import java.util.List;

import com.revature.ctb.daos.RideDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.exceptions.InputValidationException;

public class BookingServiceImpl implements BookingService {

	private RideDAO rideDao;
	
	Date today = new Date();
	
	@Override
	public void addBooking(Booking booking) {
		
		Date today = new Date();
		InputValidationException addExcep = new InputValidationException("Validation exception");
	
		if (booking.getBookingId().size() >= 2) {
			
		}
	}

	@Override
	public void deleteBooking(Integer bookingId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Booking> getAllBookingByRideId(Integer rideId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getAllBooking() {
		// TODO Auto-generated method stub
		return null;
	}

}
