package com.revature.ctb.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class BookingRestController extends BasedRestController {

	private BookingService bookingServ;

//	@Autowired
	public void setBookingServ(BookingService bookingServ) {
		this.bookingServ = bookingServ;
	}

	@PostMapping(path = "booking", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Booking postBooking(@Valid @RequestBody Booking booking) {

		// add booking
		bookingServ.addBooking(booking);

		return booking;
	}

	@DeleteMapping(path = "booking/{bookingId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteOneBooking(@PathVariable Integer bookingId) {

		// delete one booking
		bookingServ.deleteBooking(bookingId);
	}

	@DeleteMapping(path = "ride/{rideId}/booking")
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteAllBooking(@PathVariable Integer rideId) {

		// delete all booking
		bookingServ.deleteAllBookingByRideId(rideId);
	}

}
