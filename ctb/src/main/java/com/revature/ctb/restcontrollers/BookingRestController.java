package com.revature.ctb.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.services.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class BookingRestController {
	
	private BookingService bookingServ;
	
	@Autowired
	public void setBookingServ(BookingService bookingServ) {
		this.bookingServ = bookingServ;
	}
	
}
