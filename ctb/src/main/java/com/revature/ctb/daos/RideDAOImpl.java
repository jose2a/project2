package com.revature.ctb.daos;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.utils.LogUtil;

public class RideDAOImpl implements RideDAO {

	// instantiate SF to make connection available
	private SessionFactory sessionFactory;

	@Autowired
	public void SessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addRide(Ride ride) {
		LogUtil.trace("RideDAOImpl - add ride");

		// open session
		Session sess = sessionFactory.openSession();

		// save ride
		sess.save(ride);

		// if ride id > 0 , ride was created
		return ride.getRideId() > 0;
	}

	@Override
	public boolean updateRide(Ride ride) {
		LogUtil.trace("RideDAOImpl - update ride");

		Session sess = sessionFactory.openSession();

		sess.saveOrUpdate(ride);

		// if true returned, no exception occurred, and save or update must be true
		return true;
	}

	@Override
	public boolean deleteRide(Integer rideId) {
		LogUtil.trace("RideDAOImpl - delete ride");

		Session sess = sessionFactory.openSession();

		Query<Ride> query = sess.createQuery("delete from Ride where rideId :rideId", Ride.class);
		query.setParameter("rideId", rideId);

		return query.executeUpdate() > 0;
	}

	@Override
	public List<Ride> getRidesByEmpId(Integer employeeId) {
		LogUtil.trace("RideDAOImpl - get ride by Id");

		Session sess = sessionFactory.openSession();

		Employee employee = sess.get(Employee.class, employeeId);

		return employee.getRides();
	}

	@Override
	public List<Ride> getEmployeeActiveRides(Integer employeeId) {
		LogUtil.trace("RideDAOImpl - getEmployeeActiveRides");

		Session sess = sessionFactory.openSession();

		Employee employee = sess.get(Employee.class, employeeId);

		Query<Ride> query = sess.createQuery(
				"from Ride where departureDate >= :now and departureTime >= :currenttime and employee = :employee",
				Ride.class);

		String currentTimePlusTwoHrs = LocalTime.now().plusHours(2).toString();

		query.setParameter("now", new Date());
		query.setParameter("currenttime", Time.valueOf(currentTimePlusTwoHrs));
		query.setParameter("employee", employee);

		return query.getResultList();
	}

	@Override
	public List<Ride> getAllActiveRides() {
		LogUtil.trace("RideDAOImpl - getAllActiveRides");

		Session sess = sessionFactory.openSession();

		Query<Ride> query = sess.createQuery("from Ride where departureDate >= :now and departureTime >= :currenttime",
				Ride.class);

		String currentTimePlusTwoHrs = LocalTime.now().plusHours(2).toString();

		query.setParameter("now", new Date());
		query.setParameter("currenttime", Time.valueOf(currentTimePlusTwoHrs));

		return query.getResultList();
	}

	@Override
	public List<Ride> getAllRides() {
		LogUtil.trace("RideDAOImpl - get all rides");

		Session sess = sessionFactory.openSession();

		Query<Ride> query = sess.createQuery("from Ride", Ride.class);

		return query.getResultList();
	}

}
