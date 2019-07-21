package com.revature.ctb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.BookingDAO;
import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.daos.RideDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.exceptions.InputValidationException;
import com.revature.ctb.utils.LogUtil;

import javassist.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

	private BookingDAO bookingDao;
	private RideService rideService;
	private MessageService messageService;

	@Autowired
	public void setBookingDao(BookingDAO bookingDao) {
		this.bookingDao = bookingDao;
	}

	@Autowired
	public void setRideService(RideService rideService) {
		this.rideService = rideService;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Override
	public boolean addBooking(Booking booking) {

		InputValidationException inputValException = new InputValidationException("Validation exception");
		
		if (booking.getPickupLocation() == null) {
			inputValException.addError("Pickup location is required.");
		}
		
		if (booking.getDestinationLocation() == null) {
			inputValException.addError("Destination location is required.");
		}

		Employee employee = employee.getEmployeeId();

		Ride ride = rideDao.getRidebyId(rideId);

		// check if they didn't book the same day
		if (booking.getBookingId().compareTo(today)) {
			LogUtil.trace("Previous booking time = current booking");

		} else {
			LogUtil.trace("Can't schedule booking.  Conflicting time with other booking.");
			inputValException.addError("Change booking to different time");
		}

		boolean addBooking = bookingDao.addBooking(booking);

	}

	@Override
	public List<Booking> getAllBookingsByRideId(Integer rideId) {
		return bookingDao.getBookingsByRideId(rideId);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingDao.getAllBooking();
	}

	@Override
	public boolean deleteBooking(Integer bookingId) {
		return bookingDao.deleteBooking(bookingId);
	}

	@Override
	public boolean deleteAllBookingByRideId(Integer rideId) {

		for (Booking booking : bookingDao.getBookingsByRideId(rideId)) {
			bookingDao.deleteBooking(booking.getBookingId());
		}

		return true;
	}

	@Override
	public void sendMessageToDriver(Integer rideId, String message) throws NotFoundException {
		Ride ride = rideService.showRide(rideId);

		if (ride == null) {
			throw new NotFoundException("Ride not found");
		}

		messageService.sendMessage(ride.getEmployee().getPhoneNumber(), message);
	}

}
