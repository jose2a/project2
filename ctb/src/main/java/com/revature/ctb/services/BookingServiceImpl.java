package com.revature.ctb.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.daos.BookingDAO;
import com.revature.ctb.daos.EmployeeDAO;
import com.revature.ctb.daos.RideDAO;
import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.exceptions.InputValidationException;

public class BookingServiceImpl implements BookingService {

	private RideDAO rideDao;
	private BookingDAO bookingDao;
	private EmployeeService employeeService;

	@Autowired
	public void setRideDao(RideDAO sideDao) {
		this.rideDao = rideDao;
	}

	@Autowired
	public void setBookingDAO(BookingDAO bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	@Autowired
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	Date today = new Date();

	@Override
	public void addBooking(Booking booking) {

		Date today = new Date();
		InputValidationException addExcep = new InputValidationException("Validation exception");

	}

	@Override
	public List<Booking> getAllBookingByRideId(Integer rideId) {
		return bookingDao.getBookingsByRideId(rideId);
	}

	@Override
	public List<Booking> getAllBooking() {
		return bookingDao.getBookingsByRideId(null);
	}

	@Override
	public void deleteOneBooking(Integer employeeId) {
		
		List<Booking> booking = bookingDao.getBookingsByRideId(employeeId);
		
	
		
	}

	@Override
	public void deleteAllBooking(Integer rideId) {
		// TODO Auto-generated method stub
		
	}

}
