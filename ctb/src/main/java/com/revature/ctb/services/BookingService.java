package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Booking;

import javassist.NotFoundException;

public interface BookingService {

	public boolean addBooking(Booking booking);

	public boolean deleteBooking(Integer bookingId);

	public boolean deleteAllBookingByRideId(Integer rideId);

	public boolean updateBooking(Booking booking);

	public List<Booking> getAllBookingsByRideId(Integer rideId);

	public List<Booking> getAllBooking();

	public void sendMessageToDriver(Integer rideId, String message) throws NotFoundException;
	
	public List<Booking> getBookingsByEmployeeId(Integer employeeId);
	
	public boolean giveDriverFeedback(Integer rideId, Integer feedbackTypeId);
	
	public boolean givePassengerFeedback(Integer bookingId, Integer feedbackTypeId);

}
