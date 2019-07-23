package com.revature.ctb.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.services.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookingRestController extends BasedRestController{
	
	private BookingService bookingServ;
	
//	@Autowired
	public void setBookingServ(BookingService bookingServ) {
		this.bookingServ = bookingServ;
	}
	
	@PostMapping(path = "booking", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Booking postBooking(@Valid @RequestBody Booking booking) {
		
		//add booking
		bookingServ.addBooking(booking);
		
		return booking;
	}
	
	@DeleteMapping(path = "booking/{employeeId}")  
	@ResponseStatus(code = HttpStatus.CREATED)
	public Booking deleteOneBooking(@Valid @RequestBody Integer employeeId) {
		
		//delete one booking
		return bookingServ.deleteOneBooking(employeeId);
	}
	
	@DeleteMapping(path = "booking/{rideId}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Booking deleteAllBooking(@Valid @RequestBody Integer rideId) {
		
		//delete all booking
		return bookingServ.deleteAllBooking(rideId);
	}
	
	
	
}
