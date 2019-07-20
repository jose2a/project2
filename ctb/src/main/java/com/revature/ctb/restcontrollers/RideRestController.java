package com.revature.ctb.restcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.dtos.MessageDTO;
import com.revature.ctb.services.RideService;

@CrossOrigin(origins = "*")
@RestController //marks this as RC. means you can have methods in class which map to url requests
@RequestMapping("/api") 
public class RideRestController {

	
	private RideService rideServ;
	
	public void setRideServ (RideService rideServ) {
		this.rideServ =rideServ;
	}
	
	@PostMapping(value = "ride", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ride postRide(@Valid @RequestBody Ride ride) {
		
		//save ride 
		rideServ.scheduleRide(ride);
		
		return ride;
	}

	@PutMapping (value = "ride/{rideId}", consumes = "application/json" )
	@ResponseStatus(code = HttpStatus.OK)
	public Ride putRide(@PathVariable Integer rideId, @Valid @RequestBody Ride ride) {
		//updating ride
		rideServ.updateRide(ride);
		
		return ride;
	}
	
	@PutMapping(value = "ride/{rideId}/cancel", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public String cancelRide(@PathVariable Integer rideId) {
		//cancelling ride
		rideServ.cancelRide(rideId);
	
		return "ride status changed to 'Cancelled'.";
	}
	
	@GetMapping(value = "ride")
	@ResponseStatus(code = HttpStatus.OK)
	public  List<Ride> showAvailableRides(){
		return rideServ.getAllRides();
	}
	
	@GetMapping(value = "ride/employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ride> showEmployeeRides(@PathVariable Integer employeeId) {
		return rideServ.showEmployeeRides(employeeId);
	}
	
	@GetMapping(value = "ride/{rideId}")
	@ResponseStatus(code = HttpStatus.OK)
	public Ride showRide(@PathVariable Integer rideId) {
		return rideServ.showRide(rideId);
	}
	
	@GetMapping(value = "ride" )
	@ResponseStatus(code = HttpStatus.OK)
	public List<Ride> getAllRides(){
	return rideServ.getAllRides();
	}
	
	@PostMapping(value = "ride/message", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void sendMessageToPassengers (@RequestBody MessageDTO message ) {
		rideServ.driverMessageToPassengers(message.getRideId(), message.getMessage());
	}
}
