package com.revature.ctb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.daos.BookingDAO;
import com.revature.ctb.daos.CarDAO;
import com.revature.ctb.daos.RideDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.Route;
import com.revature.ctb.exceptions.InputValidationException;
import com.revature.ctb.exceptions.NotFoundRecordException;
import com.revature.ctb.utils.LogUtil;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class RideServiceImpl implements RideService {

	
	//injecting
	private CarService carService;

	private EmployeeService employeeService;	
	private RideDAO rideDao;	
	private RouteService routeService;
	private BookingDAO bookingDao;
	
	@Autowired
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@Autowired
	public void setRideDao(RideDAO rideDao) {
		this.rideDao = rideDao;
	}
	
	@Autowired
	public void setRouteService(RouteService routeService) {
		this.routeService = routeService;
	}
	
	@Autowired
	public void setRideDao(BookingDAO bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	Date today = new Date();

	
	@Override
	public void scheduleRide(Ride ride) {
		validateRide(ride);
	
		//if no exception thrown, methods will run to increase booking number & add ride
		ride.setNumberOfBookings(0);
		boolean rideAdded = rideDao.addRide(ride);
		

	}

	private void validateRide(Ride ride) {
		Date today = new Date();
		InputValidationException scheduleExcep = new InputValidationException("Validation exception");
		
		//check date for appropriate range
		if (ride.getDepartureDate().compareTo(today) > 0) {
			LogUtil.trace("Departure date is > today");
			
		} else { 
			LogUtil.trace("Can't schedule ride. Departure date outside of accepted time");
			scheduleExcep.addError("Change departure date to one day passed today");
		}
		
			
		//check that 2+ routes for ride exist
		if (ride.getRoutes().size() >= 2) {
			LogUtil.trace("Two+ routes attached to ride");	
			
		} else { //if not, add error
				LogUtil.trace("Can't schedule ride. Less than two routes attached to ride");
				scheduleExcep.addError("Ride must contain two or more routes");
		}
		
		
		//check cost for ride has been input
		if (ride.getAmountCharge() > 0) {
			LogUtil.trace("Driver has set cost for ride");
		} else {
			LogUtil.trace("Cost for ride needs to be updated");
			scheduleExcep.addError("Price for ride must be updated to greater than zero");

		}
		
		
		//check # of seats >1 
		if (ride.getNumberOfSeatsAvailable() >0) {
			LogUtil.trace("Adequate number of seats available");
		} else {
			LogUtil.trace("Insufficient number of seats available for ride");
			scheduleExcep.addError("Ride must have at least one available seat");
		}
			
		
		
		// check pickup location against destination location to ensure they are different
		Route pickupLocation = null;
		Route destinationLocation = null;
		
		for (Route r: ride.getRoutes()) {
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
		
		
		//if errors exist, throw exception
		if (scheduleExcep.getErrors().size() > 0) {
			throw scheduleExcep;
		}
	}

	@Override
	public void cancelRide(Integer rideId) {

		//change ride status to cancelled update # of bookings
		//change to soft delete?
		Ride ride= rideDao.getRidebyId(rideId);
		
		
		ride.setNumberOfBookings(ride.getNumberOfBookings()-1); //waiting on this method, send msg
		List<Booking> rideBookings = ride.getBookings();
		
		for (Booking booking : rideBookings) {
			//access employee. get phone number. send message 
			bookingDao.getBookingsByRideId(rideId).
		}
		//rideDao.deleteRide(rideId);		
//		ride.setRideStatus(rideStatus); // set status to cancel
//		BookingService to delete booking for this ride
		rideDao.updateRide(ride);
	}

	@Override
	public void updateRide(Ride ride) {
		validateRide(ride);
		//check for all exceptions that could occur when initially scheduling ride
		
		MessageServiceImpl msi = new MessageServiceImpl();
		List<Booking> rideBookings = bookingDao.getBookingsByRideId(ride.getRideId());
		for (Booking b: rideBookings) {
			b.getBookingId().r
		}
		
		
		msi.sendMessage();
    	Message message = Message.creator(
                new PhoneNumber(bookingDao.),//passenger's phone numbers
                new PhoneNumber(TWILIO_NUMBER),
                "")
                .create();
		rideDao.updateRide(ride);
	}

	
	@Override
	public List<Ride> showAvailableRides() {
		
		//check list for content
		List<Ride> showAvailable = rideDao.getAllActiveRides();
		
		return showAvailable;

	}

	@Override
	public List<Ride> showEmployeeRides(Integer employeeId) {

	
		//check list for content
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
