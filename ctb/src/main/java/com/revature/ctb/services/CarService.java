package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Car;

public interface CarService {
	
	public boolean addCar(Car car);

	public boolean updateCar(Car car);

	public boolean deleteCar(Integer carId);

	public Car getCarById(Integer carId);

	public List<Car> getCarsByEmployeeId(Integer employeeId);

}
