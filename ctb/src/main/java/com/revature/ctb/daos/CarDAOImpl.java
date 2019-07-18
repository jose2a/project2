package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.Car;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.utils.LogUtil;

public class CarDAOImpl implements CarDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCar(Car car) {
		LogUtil.trace("CarDAOImpl - addCar");

		Session session = sessionFactory.openSession();

		session.save(car);

		return car.getCarId() > 0;
	}

	@Override
	public boolean updateCar(Car car) {
		LogUtil.trace("CarDAOImpl - updateCar");
		
		Session session = sessionFactory.openSession();

		session.saveOrUpdate(car);

		return true;
	}

	@Override
	public boolean deleteCar(Integer carId) {
		LogUtil.trace("CarDAOImpl - deleteCar");
		
		Session session = sessionFactory.openSession();

		Query<Car> query = session.createQuery("delete from Car where carId = :carId", Car.class);
		query.setParameter("carId", carId);

		return query.executeUpdate() > 0;
	}

	@Override
	public Car getCarById(Integer carId) {
		LogUtil.trace("CarDAOImpl - getCarById");
		
		Session session = sessionFactory.openSession();

		Car car = null;

		car = session.get(Car.class, carId);

		return car;
	}

	@Override
	public List<Car> getCarsByEmployeeId(Integer employeeId) {
		LogUtil.trace("CarDAOImpl - getCarsByEmployeeId");

		Session session = sessionFactory.openSession();

		Employee employee = session.get(Employee.class, employeeId);
		
		// Getting cars by employeeId and are active
		Query<Car> query = session.createQuery("from Car c where c.Employee = :employee and active = true", Car.class);

		query.setParameter("employee", employee);

		return query.getResultList();
	}

	@Override
	public Car getCarByVinNumber(String vinNumber) {
		LogUtil.trace("CarDAOImpl - getCarByVinNumber");

		Session session = sessionFactory.openSession();

		Query<Car> query = session.createQuery("from Car c where c.vinNumber = :vinNumber", Car.class);

		query.setParameter("vinNumber", vinNumber);

		Car car = null;

		if (query.getResultList().size() > 0) {
			car = query.getSingleResult();
		}

		return car;
	}

}
