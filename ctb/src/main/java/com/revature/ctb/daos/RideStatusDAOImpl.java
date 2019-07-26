package com.revature.ctb.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.RideStatus;

@Repository
public class RideStatusDAOImpl implements RideStatusDAO {
	
	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public RideStatus getRideStatus(Integer rideStatusId) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(RideStatus.class, rideStatusId);
	}


}
