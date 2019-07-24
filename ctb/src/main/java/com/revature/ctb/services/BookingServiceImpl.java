package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.BookingDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.FeedbackType;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.RideStatus;
import com.revature.ctb.exceptions.InputValidationException;
import com.revature.ctb.exceptions.NotFoundRecordException;
import com.revature.ctb.utils.LogUtil;

import javassist.NotFoundException;

@Service
public class BookingServiceImpl implements BookingService {

	private static final int MAX_NUMBER_OF_FLAGS = 5;
	private BookingDAO bookingDao;
	private RideService rideService;
	private FeedbackTypeService feedbackTypeService;
	private EmployeeService employeeService;
	private RideStatusService rideStatusService;

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
	public void setFeedbackTypeService(FeedbackTypeService feedbackTypeService) {
		this.feedbackTypeService = feedbackTypeService;
	}

	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Autowired
	public void setRideStatusService(RideStatusService rideStatusService) {
		this.rideStatusService = rideStatusService;
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

		Ride ride = rideService.showRide(booking.getRide().getRideId());

		if (ride.getNumberOfBookings() == ride.getNumberOfSeatsAvailable()) {
			inputValException.addError("Sorry, this ride is full. You can look for some other options in the system.");
		}

		if (inputValException.getErrors().size() > 0) {
			throw inputValException;
		}

		boolean added = bookingDao.addBooking(booking);

		if (added) {
			ride.setNumberOfBookings(ride.getNumberOfBookings() + 1); // incrementing the number of bookings

			rideService.updateRide(ride);
		}

		return added;

	}

	private void validateBookingDate(Booking booking, InputValidationException inputValException) {
		List<Booking> bookingsForEmployee = bookingDao
				.getFutureBookingsByEmployeeId(booking.getEmployee().getEmployeeId());

		Ride ride = rideService.showRide(booking.getRide().getRideId());

		// We need to find out if this ride is not crossing with another ride the
		// employee scheduled before
		for (Booking bookedRide : bookingsForEmployee) {
			if (bookedRide.getRide().getDepartureDate().equals(ride.getDepartureDate())) {
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

	@Override
	public boolean giveDriverFeedback(Integer bookingId, Integer feedbackTypeId) {
		Booking booking = bookingDao.getBookingById(bookingId);

		if (booking == null) {
			throw new NotFoundRecordException("Booking not found");
		}

		FeedbackType feedbackType = feedbackTypeService.geFeedbackType(feedbackTypeId);

		booking.setDriverFeedback(feedbackType);

		bookingDao.updateBooking(booking);

		List<Booking> bookingsForRide = bookingDao.getBookingsByRideId(booking.getRide().getRideId());

		// Update driver status base on this review
		updateDriverStatus(booking, bookingsForRide);

		// Update ride status if feedback from all passengers is received
		updateRideStatus(booking, bookingsForRide);

		return true;
	}

	/**
	 * Decides whether the driver is getting a flag based on the reviews for this
	 * ride
	 * 
	 * @param booking
	 */
	private void updateDriverStatus(Booking booking, List<Booking> bookingsForRide) {

		int numberOfBadFeedbacks = 0;

		// Check if the review is one or two stars, it is considered bad review
		for (Booking rideBooking : bookingsForRide) {
			if (rideBooking.getDriverFeedback().getFeedbackTypeId() == FeedbackType.FeedbackTypeId.TWO_STAR
					|| rideBooking.getDriverFeedback().getFeedbackTypeId() == FeedbackType.FeedbackTypeId.ONE_STAR) {

				numberOfBadFeedbacks++;
			}
		}

		// If number of bad feedbacks are equal to the total number of bookings,
		// we give one more flag to the driver
		if (numberOfBadFeedbacks == bookingsForRide.size()) {
			Employee employee = booking.getRide().getEmployee();
			employee.setNumberOfFlags(employee.getNumberOfFlags() + 1);

			// If driver has reached the maximum amount of flags, it gets blocked by the
			// system
			if (employee.getNumberOfFlags() == MAX_NUMBER_OF_FLAGS) {
				employee.setBlocked(true);
			}

			// update the employee
			employeeService.updateEmployee(employee);
		}
	}

	/**
	 * Updating ride status based on the number of reviews received by the
	 * passengers. If the number of reviews are equals to the number of bookings the
	 * ride is considered to be completed.
	 * 
	 * @param booking The booking
	 */
	private void updateRideStatus(Booking booking, List<Booking> bookingsForRide) {
		int numberOfFeedbacks = 0;

		// Check if we received all feedbacks from the passengers
		for (Booking rideBooking : bookingsForRide) {
			if (rideBooking.getDriverFeedback() != null) {
				numberOfFeedbacks++;
			}
		}

		// we received all the feedbacks
		if (numberOfFeedbacks == bookingsForRide.size()) {
			Ride ride = booking.getRide();

			ride.setRideStatus(rideStatusService.getRideStatus(RideStatus.RideStatusIds.COMPLETED));

			// update ride status
			rideService.updateRide(ride);
		}
	}

	@Override
	public boolean givePassengerFeedback(Integer bookingId, Integer feedbackTypeId) {
		Booking booking = bookingDao.getBookingById(bookingId);

		if (booking == null) {
			throw new NotFoundRecordException("Booking not found");
		}

		FeedbackType feedbackType = feedbackTypeService.geFeedbackType(feedbackTypeId);

		// Setting passenger feedback
		booking.setPassengerFeedback(feedbackType);

		// Update booking
		bookingDao.updateBooking(booking);

		// update passenger status
		updatePassengerStatus(booking, feedbackType);

		return true;
	}

	/**
	 * Deciding if the passenger gets more flags based on this review, or if this
	 * gets blocked
	 * 
	 * @param booking
	 * @param feedbackType
	 */
	private void updatePassengerStatus(Booking booking, FeedbackType feedbackType) {
		// Check if the passenger gets a bad feedback from the driver, we give a flag
		if (feedbackType.getFeedbackTypeId() == FeedbackType.FeedbackTypeId.ONE_STAR
				|| feedbackType.getFeedbackTypeId() == FeedbackType.FeedbackTypeId.TWO_STAR) {

			Employee employee = booking.getEmployee();
			employee.setNumberOfFlags(employee.getNumberOfFlags() + 1); // Give a flag to the passenger

			// If passenger reached the maximum amount of flags, it gets blocked
			if (employee.getNumberOfFlags() == MAX_NUMBER_OF_FLAGS) {
				employee.setBlocked(true); // block the passenger
			}

			employeeService.updateEmployee(employee); // update the employee
		}
	}

}
