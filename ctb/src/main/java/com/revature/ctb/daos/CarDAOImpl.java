package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.Car;
import com.revature.ctb.domains.Employee;

public class CarDAOImpl implements CarDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCar(Car car) {
		Session session = sessionFactory.getCurrentSession();

		session.save(car);

		return car.getCarId() > 0;
	}

	@Override
	public boolean updateCar(Car car) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(car);

		return true;
	}

	@Override
	public boolean deleteCar(Integer carId) {
		Session session = sessionFactory.getCurrentSession();

		Query<Car> query = session.createQuery("delete from Car where carId = :carId", Car.class);
		query.setParameter("carId", carId);

		return query.executeUpdate() > 0;
	}

	@Override
	public Car getCarById(Integer carId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Car.class, carId);
	}

	@Override
	public List<Car> getCarsByEmployeeId(Integer employeeId) {
		Session session = sessionFactory.getCurrentSession();

		Employee employee = session.get(Employee.class, employeeId);

		// Getting cars by employeeId and are active
		Query<Car> query = session.createQuery("from Car c where c.Employee = :employee and active = true", Car.class);

		query.setParameter("employee", employee);

		return query.getResultList();
	}

	@Override
	public Car getCarByVinNumber(String vinNumber) {
		Session session = sessionFactory.getCurrentSession();

		Query<Car> query = session.createQuery("from Car c where c.vinNumber = :vinNumber", Car.class);

		query.setParameter("vinNumber", vinNumber);

		Car car = null;

		if (query.getResultList().size() > 0) {
			car = query.getSingleResult();
		}

		return car;
	}

}
