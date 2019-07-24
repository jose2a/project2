package com.revature.ctb.restcontrollers;

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

import com.revature.ctb.domains.Car;
import com.revature.ctb.services.CarService;

@RestController
public class CarRestController extends BasedRestController {
	
	private CarService carService; 
	
	@Autowired
	public void setCarService (CarService carService) {
		this.carService = carService;
	}
	
	
	@PostMapping (value="/car", consumes="application/json")
	@ResponseStatus(code=HttpStatus.CREATED)
	public Car newCar(@Valid @RequestBody Car car) {
		
		carService.addCar(car);
		
		return car;
	}

	@PutMapping (value = "/car")
	@ResponseStatus(code=HttpStatus.OK)
	public Car changeCar(Car car) {
		carService.updateCar(car);
		
		return car;
	}

	
	//Double check
	@DeleteMapping (value = "/car/{carId}")
	@ResponseStatus(code=HttpStatus.OK)
	public String dropCar(Integer carId, @Valid @RequestBody Car car) {
		
		carService.deleteCar(carId); 
		
		return "Car Removal Succesful"; 
	}

	@GetMapping (value = "/car/{carId}")
	@ResponseStatus(code=HttpStatus.OK)
	public Car getCarById(@PathVariable Integer carId) {
		
		return carService.getCarById(carId);
	}

	@GetMapping (value = "/car/employee/{employeeId}")
	@ResponseStatus(code=HttpStatus.OK)
	public List<Car> getCarsByEmployeeId(@PathVariable Integer employeeId){
		
		List<Car> carList = carService.getCarsByEmployeeId(employeeId);
		
		return carList;
	}
	
	
	

}
