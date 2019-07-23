package com.revature.ctb.services;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.RideDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Car;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.RideStatus;
import com.revature.ctb.domains.Route;
import com.revature.ctb.exceptions.InputValidationException;
import com.revature.ctb.exceptions.NotFoundRecordException;
import com.revature.ctb.utils.LogUtil;

@Service
public class RideServiceImpl implements RideService {

	// injecting
	private CarService carService;
	private RideDAO rideDao;
	private BookingService bookingService;
	private MessageService messageService;
	private RideStatusService rideStatusService;
	private RouteService routeService;

	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	@Autowired
	public void setRideDao(RideDAO rideDao) {
		this.rideDao = rideDao;
	}

	@Autowired
	public void setBookingService(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@Autowired
	public void setRideStatusService(RideStatusService rideStatusService) {
		this.rideStatusService = rideStatusService;
	}

	@Autowired
	public void setRouteService(RouteService routeService) {
		this.routeService = routeService;
	}

	@Override
	public void scheduleRide(Ride ride) {
		validateRide(ride);

		// if no exception thrown, methods will run to increase booking number & add
		// ride
		ride.setNumberOfBookings(0);
		ride.setRideStatus(rideStatusService.getRideStatus(RideStatus.RideStatusIds.ACTIVE));

		List<Route> routes = ride.getRoutes();
		ride.setRoutes(null);

		boolean added = rideDao.addRide(ride);

		if (added) {
			routes.stream().forEach(r -> r.setRide(ride));
			routeService.addRoutes(routes);
		}
	}

	private void validateRide(Ride ride) {
		Date today = new Date();
		InputValidationException scheduleExcep = new InputValidationException("Validation exception");

		// check date for appropriate range
		if (ride.getDepartureDate().compareTo(today) > 0) {
			LogUtil.trace("Departure date is > today");

		} else {
			LogUtil.trace("Can't schedule ride. Departure date outside of accepted time");
			scheduleExcep.addError("Change departure date to one day passed today");
		}

		// check that 2+ routes for ride exist
		if (ride.getRoutes().size() < 2) {
			// if not, add error
			LogUtil.trace("Can't schedule ride. Less than two routes attached to ride");
			scheduleExcep.addError("Ride must contain two or more routes");
		}

		// check cost for ride has been input
		if (ride.getAmountCharge() < 0) {
			LogUtil.trace("Cost for ride needs to be updated");
			scheduleExcep.addError("Price for ride must be updated to greater than zero");
		}

		// check # of seats >1
		if (ride.getNumberOfSeatsAvailable() < 0) {
			LogUtil.trace("Insufficient number of seats available for ride");
			scheduleExcep.addError("Ride must have at least one available seat");
		}

		Car car = carService.getCarById(ride.getCar().getCarId());

		if (ride.getNumberOfSeatsAvailable() > (car.getNumberOfSeats() - 1)) {
			LogUtil.trace("Assigning more seats available for ride thant the ones the car have minus the driver seat");
			scheduleExcep.addError("You don't have more available seats");
		}

		// check pickup location against destination location to ensure they are
		// different
		Route pickupLocation = null;
		Route destinationLocation = null;

		for (Route r : ride.getRoutes()) {
			if (r.isPickupLocation()) {
				pickupLocation = r;
			}
			if (r.isDestinationLocation()) {
				destinationLocation = r;
			}
			if (pickupLocation == destinationLocation) {
				scheduleExcep.addError("Pickup location and destination location must be different");
			}
		}

		// if errors exist, throw exception
		if (scheduleExcep.getErrors().size() > 0) {
			throw scheduleExcep;
		}
	}

	@Override
	public void cancelRide(Integer rideId) {

		// change ride status to cancelled update # of bookings
		// change to soft delete?
		Ride ride = rideDao.getRidebyId(rideId);

		String message = "Your ride scheduled was cancelled";

		sendMessageToPassengers(ride.getBookings(), message);

		bookingService.deleteAllBookingByRideId(rideId);

		// cancelled ride
		ride.setRideStatus(rideStatusService.getRideStatus(RideStatus.RideStatusIds.CANCELED));
		rideDao.updateRide(ride);
	}

	// sending message to passengers from system
	@Override
	public void sendMessageToPassengers(List<Booking> bookings, String message) {
		for (Booking booking : bookings) {
			// access employee. get phone number. send message
			Employee emp = booking.getEmployee();

			messageService.sendMessage(emp.getPhoneNumber(), message);
		}
	}

	// driver sends message to passengers
	@Override
	public void driverMessageToPassengers(Integer rideId, String message) {

		Ride thisRide = rideDao.getRidebyId(rideId);

		sendMessageToPassengers(thisRide.getBookings(), message);

	}

	@Override
	public void updateRide(Ride ride) {
		validateRide(ride);
		// check for all exceptions that could occur when initially scheduling ride
		List<Route> routes = ride.getRoutes();
		ride.setRoutes(null);
		
		boolean updated = rideDao.updateRide(ride);

		if (updated) {
			routes.forEach(r -> r.setRide(ride));
			routeService.addRoutes(routes);

			String message = "Your ride scheduled was updated. ";

			sendMessageToPassengers(ride.getBookings(), message);

		}
	}

	@Override
	public List<Ride> showAvailableRides() throws ParseException {

		// check list for content
		List<Ride> showAvailable = rideDao.getAllActiveRides();

		return showAvailable;

	}

	@Override
	public List<Ride> showEmployeeRides(Integer employeeId) {

		// check list for content
		List<Ride> employeeRides = rideDao.getRidesByEmpId(employeeId);

		return employeeRides;

	}

	@Override
	public Ride showRide(Integer rideId) {

		Ride ride = rideDao.getRidebyId(rideId);
		if (ride.equals(null)) {
			throw new NotFoundRecordException();
		}

		return ride;
	}

	@Override
	public List<Ride> getAllRides() {

		List<Ride> allRides = rideDao.getAllRides();

		return allRides;
	}
}
