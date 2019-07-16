package com.revature.ctb.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.RideStatus;

public class RideStatusDAOImpl implements RideStatusDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RideStatusDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	public RideStatus getRideStatus(Integer rideStatusId) {
		Session session = sessionFactory.openSession();
		
		return session.get(RideStatus.class, rideStatusId);
	}


}
