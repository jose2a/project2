package com.revature.ctb.restcontrollers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.Ride;
import com.revature.ctb.dtos.MessageDto;
import com.revature.ctb.dtos.RideDto;
import com.revature.ctb.services.RideService;
import com.revature.ctb.utils.LogUtil;

@RestController // marks this as RC. means you can have methods in class which map to url
				// requests
public class RideRestController extends BasedRestController {

	private RideService rideServ;

	@Autowired
	public void setRideServ(RideService rideServ) {
		this.rideServ = rideServ;
	}

	@PostMapping(value = "ride", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public RideDto postRide(@Valid @RequestBody Ride ride) {

		LogUtil.debug(">>>>>>>>> " + ride.toString());

		// save ride
		rideServ.scheduleRide(ride);

		return mapRideToRideDto(ride);
	}

	@PutMapping(value = "ride/{rideId}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public RideDto putRide(@PathVariable Integer rideId, @Valid @RequestBody Ride ride) {
		// updating ride
		rideServ.updateRide(ride);

		return mapRideToRideDto(ride);
	}

	@PutMapping(value = "ride/{rideId}/cancel", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void cancelRide(@PathVariable Integer rideId) {
		// Canceling ride
		rideServ.cancelRide(rideId);
	}

	@GetMapping(value = "ride/available")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RideDto> showAvailableRides() throws ParseException {

		List<RideDto> rideList = new ArrayList<>();

		rideServ.showAvailableRides().stream().forEach(r -> rideList.add(mapRideToRideDto(r)));

		return rideList;
	}

	@GetMapping(value = "ride/employee/{employeeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RideDto> showEmployeeRides(@PathVariable Integer employeeId) {
		List<RideDto> rideList = new ArrayList<>();

		rideServ.showEmployeeRides(employeeId).stream().forEach(r -> rideList.add(mapRideToRideDto(r)));

		return rideList;
	}

	@GetMapping(value = "ride/{rideId}")
	@ResponseStatus(code = HttpStatus.OK)
	public RideDto showRide(@PathVariable Integer rideId) {
		Ride ride = rideServ.showRide(rideId);

		return mapRideToRideDto(ride);
	}

	@GetMapping(value = "ride")
	@ResponseStatus(code = HttpStatus.OK)
	public List<RideDto> getAllRides() {
		List<RideDto> rideList = new ArrayList<>();

		rideServ.getAllRides().stream().forEach(r -> rideList.add(mapRideToRideDto(r)));

		return rideList;
	}

	@PostMapping(value = "ride/message", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void sendMessageToPassengers(@RequestBody MessageDto message) {
		rideServ.driverMessageToPassengers(message.getRideId(), message.getMessage());
	}
}
