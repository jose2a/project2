package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Car;

public interface CarDAO {

	public boolean addCar(Car car);

	public boolean updateCar(Car car);

	public boolean deleteCar(Integer carId);

	public Car getCar(Integer carId);

	public List<Car> getCarsByEmployeeId(Integer employeeId);

}
