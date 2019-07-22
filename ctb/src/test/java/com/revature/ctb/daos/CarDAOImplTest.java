package com.revature.ctb.daos;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.ctb.domains.Car;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.utils.SessionFactoryUtil;

public class CarDAOImplTest {

	private CarDAOImpl carDao;
	private EmployeeDAOImpl employeeDao;
	private Integer employeeId = 73;
	
//	@Before
	public void setUp() throws Exception {
		carDao = new CarDAOImpl();
		carDao.setSessionFactory(SessionFactoryUtil.getSessionFactory());
		
		employeeDao = new EmployeeDAOImpl();
		employeeDao.setSessionFactory(SessionFactoryUtil.getSessionFactory());
	}

//	@Test
	public void addCar_Valid_ShouldReturnTrue() {
		Car car = new Car("12DFRR", "Toyota", "Corolla", 4, true, true);
		
		Employee emp = new Employee(); //employeeDao.getEmployeeById(employeeId);
		emp.setEmployeeId(employeeId);
		
		car.setEmployee(emp);
		
		boolean result = carDao.addCar(car);
		
		assertTrue(result);
	}

}
