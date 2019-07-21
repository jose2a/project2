package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.BookingDAO;
import com.revature.ctb.domains.Booking;
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

		validateBookingLocations(booking, inputValException);

		validateBookingDate(booking, inputValException);

		if (inputValException.getErrors().size() > 0) {
			throw inputValException;
		}

		return bookingDao.addBooking(booking);
	}

	private void validateBookingDate(Booking booking, InputValidationException inputValException) {
		List<Booking> bookingsForEmployee = bookingDao
				.getFutureBookingsByEmployeeId(booking.getEmployee().getEmployeeId());

		Ride ride = rideService.showRide(booking.getRide().getRideId());

		// We need to find out if this ride is not crossing with another ride the
		// employee scheduled before
		for (Booking bookedRide : bookingsForEmployee) {
			if (bookedRide.getRide().getDepartureDate().before(ride.getDepartureDate())) {
				inputValException.addError("You have scheduled another ride, which is leaving on or before this ride.");

				LogUtil.debug("This booking is crossing with another ride the user already booked");

				break; // if we find one crossing, we don't need to continue
			}
		}
	}

	private void validateBookingLocations(Booking booking, InputValidationException inputValException) {
		if (booking.getPickupLocation() == null) {
			inputValException.addError("Pickup location is required.");
		}

		if (booking.getDestinationLocation() == null) {
			inputValException.addError("Destination location is required.");
		}

		if (booking.getPickupLocation() == booking.getDestinationLocation()) {
			inputValException.addError("Pick up and destination should not be the same");
		}
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

	@Override
	public List<Booking> getBookingsByEmployeeId(Integer employeeId) {
		return bookingDao.getBookingsByEmployeeId(employeeId);
	}

	@Override
	public boolean updateBooking(Booking booking) {
		InputValidationException inputValException = new InputValidationException("Validation exception");

		validateBookingLocations(booking, inputValException);

		if (inputValException.getErrors().size() > 0) {
			throw inputValException;
		}

		return bookingDao.updateBooking(booking);

	}

}
