package com.revature.ctb.restcontrollers;

import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.services.CarService;

@RestController
public class CarRestController extends BasedRestController {
	
	private CarService carService; 
	
	public void setCarService (CarService carService) {
		this.carService = carService;
	}
	
	
	
	
	
	

}
