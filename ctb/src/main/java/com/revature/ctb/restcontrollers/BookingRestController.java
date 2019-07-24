package com.revature.ctb.restcontrollers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.dtos.BookingDto;
import com.revature.ctb.services.BookingService;

@RestController
public class BookingRestController extends BasedRestController {

	private BookingService bookingServ;

	@Autowired
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

	@PutMapping(path = "booking/{bookingId}")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateBooking(@PathVariable Integer bookingId, @Valid @RequestBody Booking booking) {

		bookingServ.updateBooking(booking);
	}

	@GetMapping(value = "ride/{rideId}/booking")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookingDto> getAllBookingsByRideId(@PathVariable Integer rideId) {
		List<BookingDto> bookings = new ArrayList<BookingDto>();

		bookingServ.getAllBookingsByRideId(rideId).forEach(b -> bookings.add(mapper.map(b, BookingDto.class)));

		return bookings;
	}

	@GetMapping(value = "booking")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookingDto> getAllBooking() {
		List<BookingDto> bookings = new ArrayList<BookingDto>();

		bookingServ.getAllBooking().forEach(b -> bookings.add(mapper.map(b, BookingDto.class)));

		return bookings;
	}

	@GetMapping(value = "employee/{employeeId}/booking")
	@ResponseStatus(code = HttpStatus.OK)
	public List<BookingDto> getBookingsByEmployeeId(@PathVariable Integer employeeId) {
		List<BookingDto> bookings = new ArrayList<BookingDto>();

		bookingServ.getBookingsByEmployeeId(employeeId).forEach(b -> bookings.add(mapper.map(b, BookingDto.class)));

		return bookings;
	}

//	public void sendMessageToDriver(Integer rideId, String message) throws NotFoundException;

//	public boolean giveDriverFeedback(Integer rideId, Integer feedbackTypeId);

//	public boolean givePassengerFeedback(Integer bookingId, Integer feedbackTypeId);

}
