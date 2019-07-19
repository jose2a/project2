package com.revature.ctb.restcontrollers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Ride;
import com.revature.ctb.services.RideService;

@CrossOrigin(origins = "*")
@RestController ////marks this as RC. means you can have methods in class which map to url requests
@RequestMapping("/api") 
public class RideRestController {

	
	private RideService rideServ;
	
	public void setRideServ (RideService rideServ) {
		this.rideServ =rideServ;
	}
	
	@PostMapping(path = "ride", consumes = "application/json")
	
	@ResponseStatus(code = HttpStatus.CREATED)
	public Ride postRide(@Valid @RequestBody Ride ride) {
		
		//save ride 
		rideServ.scheduleRide(ride);
		
		return ride;
	}
}
