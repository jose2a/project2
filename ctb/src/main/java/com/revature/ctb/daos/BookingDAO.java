package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Booking;

public interface BookingDAO {

	public boolean addBooking(Booking booking);

	public boolean updateBooking(Booking booking);

	public boolean deleteBooking(Booking booking);

	public List<Booking> getBookingsByRideId(Integer rideId);

}
