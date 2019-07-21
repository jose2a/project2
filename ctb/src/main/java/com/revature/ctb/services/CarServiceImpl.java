package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.CarDAO;
import com.revature.ctb.domains.Car;
import com.revature.ctb.exceptions.DuplicateRecordException;
import com.revature.ctb.exceptions.NotFoundRecordException;
import com.revature.ctb.utils.LogUtil;

@Service
public class CarServiceImpl implements CarService {

	private CarDAO carDao;

	@Autowired
	public void setCarDao(CarDAO carDao) {
		this.carDao = carDao;
	}

	@Override
	public boolean addCar(Car car) {

		boolean carExist = carDao.getCarByVinNumber(car.getVinNumber()) != null;

		if (carExist) {
			LogUtil.debug(">>>>>>>>>> Car with the same vin number already exist");

			DuplicateRecordException drExc = new DuplicateRecordException();
			drExc.addError("You added a car with the same vin number already.");

			throw drExc;
		}

		car.setActive(true);

		return carDao.addCar(car);
	}

	@Override
	public boolean updateCar(Car car) {
		return carDao.updateCar(car);
	}

	@Override
	public boolean deleteCar(Integer carId) {
		Car car = carDao.getCarById(carId);

		if (car == null) {
			throw new NotFoundRecordException("Car not found");
		}

		car.setActive(false); // Soft delete the car

		return carDao.updateCar(car);
	}

	@Override
	public Car getCarById(Integer carId) {
		return carDao.getCarById(carId);
	}

	@Override
	public List<Car> getCarsByEmployeeId(Integer employeeId) {
		return carDao.getCarsByEmployeeId(employeeId);
	}

}
