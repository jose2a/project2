package com.revature.ctb.daos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.utils.LogUtil;

@Repository
public class RideDAOImpl implements RideDAO {

	// instantiate SF to make connection available
	private SessionFactory sessionFactory;

	@Autowired
	public void SessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addRide(Ride ride) {
		Session session = sessionFactory.getCurrentSession();

		// save ride
		session.save(ride);

		// if ride id > 0 , ride was created
		return ride.getRideId() > 0;
	}

	@Override
	public boolean updateRide(Ride ride) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(ride);

		// if true returned, no exception occurred, and save or update must be true
		return true;
	}

	@Override
	public boolean deleteRide(Integer rideId) {
		Session session = sessionFactory.getCurrentSession();

		Query<Ride> query = session.createQuery("delete from Ride where rideId :rideId", Ride.class);
		query.setParameter("rideId", rideId);

		return query.executeUpdate() > 0;
	}

	@Override
	public List<Ride> getRidesByEmpId(Integer employeeId) {
		Session session = sessionFactory.getCurrentSession();

		Employee employee = session.get(Employee.class, employeeId);
		employee.getRides().toString(); // loading the rides

		return employee.getRides();
	}

	@Override
	public Ride getRidebyId(Integer rideId) {
		Session session = sessionFactory.getCurrentSession();

		Query<Ride> query = session.createQuery("from Ride where rideId = :rideId", Ride.class);
		query.setParameter("rideId", rideId);

		Ride ride = (Ride) query.getSingleResult();
		ride.getRoutes().toString();

		return ride;
	}

	@Override
	public List<Ride> getEmployeeActiveRides(Integer employeeId) throws ParseException {
		Session session = sessionFactory.getCurrentSession();

		Employee employee = session.get(Employee.class, employeeId);

		Query<Ride> query = session.createQuery(
				"from Ride where departureDate >= :now and departureTime >= :currenttime and employee = :employee",
				Ride.class);

		String currentTimePlusTwoHrs = LocalTime.now().plusHours(2).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");

		LogUtil.debug(">>>>>> Current time: " + currentTimePlusTwoHrs);

		query.setParameter("now", new Date());
		query.setParameter("currenttime", sdf.parse(currentTimePlusTwoHrs));
		query.setParameter("employee", employee);

		return query.getResultList();
	}

	@Override
	public List<Ride> getAllActiveRides() throws ParseException {
		Session session = sessionFactory.getCurrentSession();

		Query<Ride> query = session
				.createQuery("from Ride where departureDate >= :now and departureTime >= :currenttime", Ride.class);

		String currentTimePlusTwoHrs = LocalTime.now().plusHours(2).toString();

		SimpleDateFormat sdf = new SimpleDateFormat("hh:MM:ss");

		LogUtil.debug(">>>>>> Current time: " + currentTimePlusTwoHrs);

		query.setParameter("now", new Date());
		query.setParameter("currenttime", sdf.parse(currentTimePlusTwoHrs));

		return query.getResultList();
	}

	@Override
	public List<Ride> getAllRides() {
		Session session = sessionFactory.getCurrentSession();

		Query<Ride> query = session.createQuery("from Ride", Ride.class);

		return query.getResultList();
	}

}
