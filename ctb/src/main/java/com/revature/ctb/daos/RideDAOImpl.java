package com.revature.ctb.daos;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.ctb.domains.Employee;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.utils.LogUtil;

public class RideDAOImpl implements RideDAO {
	
	LocalDateTime now = LocalDateTime.now();

	
	//instantiate SF to make connection available
	private SessionFactory sessionFactory;
	
	public void SessionFactory (SessionFactory sessionFactory) {
		this.sessionFactory= sessionFactory;
	}
	
	@Override
	public boolean addRide(Ride ride) {
		LogUtil.trace("RideDAOImpl - add ride");
		
		//open session
		Session sess = sessionFactory.openSession();
		
		//save ride
		sess.save(ride);
		
		//if ride id > 0 , ride was created
		return ride.getRideId() >0;
	}

	@Override
	public boolean updateRide(Ride ride) {
		LogUtil.trace("RideDAOImpl - update ride");
		Session sess = sessionFactory.openSession();
		
		sess.saveOrUpdate(ride);
		
		//if true returned, no exception occurred, and save or update must be true
		return true;
	}

	@Override
	public boolean deleteRide(Integer rideId) {
		LogUtil.trace("RideDAOImpl - delete ride");
		
		Session sess = sessionFactory.openSession();
		
		Query query = sess.createQuery("delete from Ride where rideId :rideId");
		query.setParameter("rideId", rideId);
		return query.executeUpdate() > 0;
	}

	@Override
	public List<Ride> getRidesByEmpId(Integer employeeId) {
		LogUtil.trace("RideDAOImpl - get ride by Id");
		// TODO Auto-generated method stub
		Session sess = sessionFactory.openSession();
		
		Employee employee = sess.get(Employee.class, employeeId);
		
		org.hibernate.query.Query<Ride> query = sess.createQuery("from Ride r where r.Employee = :employee",
				Ride.class);
		
		query.setParameter("employee", employee);
		return query.getResultList();
	}

	@Override
	public List<Ride> getEmployeeRideByDate(Integer employeeId, Date departureDate) {
		LogUtil.trace("RideDAOImpl - get employee ride by date");
		
		Session sess = sessionFactory.openSession();
		
		Employee employee = sess.get(Employee.class, employeeId);
		
		org.hibernate.query.Query<Ride> query = sess.createQuery("FROM Ride where departureDate > :now AND employee = :employee", Ride.class);
		query.setParameter("now", new Date());
		query.setParameter("employee", employee);
		return query.getResultList() ;
	}
	
	@Override
	public List<Ride> getRideByDate(Date departureDate) {
		LogUtil.trace("RideDAOImpl - get employee ride by date");
		
		Session sess = sessionFactory.openSession();
		
		
		org.hibernate.query.Query<Ride> query = sess.createQuery("FROM Ride where departureDate > :now", Ride.class);
		query.setParameter("now", new Date());
		return query.getResultList() ;
	}

	@Override
	public List<Ride> getAllRides() {
		LogUtil.trace("RideDAOImpl - get all rides");

		Session sess = sessionFactory.openSession();
		String hql = "FROM Ride";
		Query query = sess.createQuery(hql);
		
		List<Ride> rides= query.getResultList();
		
		return rides;
	}

}
