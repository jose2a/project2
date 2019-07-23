package com.revature.ctb.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Car;
import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.EmployeeRole;
import com.revature.ctb.domains.FeedbackType;
import com.revature.ctb.domains.InfoReq;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.RideStatus;
import com.revature.ctb.domains.Role;
import com.revature.ctb.domains.Route;

public class SessionFactoryUtil {

	private SessionFactory sessionFactory;

	public SessionFactoryUtil() {
		Configuration configuration = new Configuration().configure();
		configuration.setProperty("hibernate.connection.username", System.getenv("CTB_USERNAME"));
		configuration.setProperty("hibernate.connection.password", System.getenv("CTB_PASSWORD"));
		configuration.setProperty("hibernate.connection.url",
				"jdbc:postgresql://" + System.getenv("CTB_URL") + ":5412/tan_1905java");

		sessionFactory = configuration.configure("hibernate.cfg.xml").addAnnotatedClass(Booking.class)
				.addAnnotatedClass(Car.class).addAnnotatedClass(Employee.class).addAnnotatedClass(EmployeeRole.class)
				.addAnnotatedClass(FeedbackType.class).addAnnotatedClass(InfoReq.class).addAnnotatedClass(Ride.class)
				.addAnnotatedClass(RideStatus.class).addAnnotatedClass(Role.class).addAnnotatedClass(Route.class)
				.buildSessionFactory();

		LogUtil.debug("Created session factory");
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
