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

@Service
public class BookingServiceImpl implements BookingService {

	private RideDAO rideDao;
	private BookingDAO bookingDao;
	private EmployeeService employeeService;
	private Employee employee;
	private MessageService messageService;

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
	
	@Autowired
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	Date today = new Date();

	@Override
	public void addBooking(Booking booking) {

		Date today = new Date();
		InputValidationException addExcep = new InputValidationException("Validation exception");
		
		 Employee employee = employee.getEmployeeId();
		 
		 Ride ride = rideDao.getRidebyId(rideId);
		 
		//check if they didn't book the same day
		if(booking.getBookingId().compareTo(today)) {
			LogUtil.trace("Previous booking time = current booking");
			
		} else {
			LogUtil.trace("Can't schedule booking.  Conflicting time with other booking.");
			addExcep.addError("Change booking to different time");
		}
		
		boolean addBooking = bookingDao.addBooking(booking);
		
	}

	@Override
	public List<Booking> getAllBookingByRideId(Integer rideId) {
		
		return bookingDao.getBookingsByRideId(rideId);	
	}

	@Override
	public List<Booking> getAllBooking() {
		
		List<Booking> allBooking = bookingDao.getAllBooking();
		
		return allBooking;
	}

	@Override
	public void deleteOneBooking(Integer employeeId) {
		
		List<Booking> booking = bookingDao.getBookingsByRideId(employeeId);
		for(Booking employee : booking) {
		}
		bookingDao.deleteBooking(null);
	}

	@Override
	public void deleteAllBooking(Integer rideId) {
		
		List<Booking> booking = bookingDao.getBookingsByRideId(rideId); 
		
	}

	@Override
	public void sendMessageToDriver(Ride ride, String message) {
		
		Employee driver = ride.getEmployee();
		
		messageService.sendMessage(driver.getPhoneNumber(), message);
		
	}

}
