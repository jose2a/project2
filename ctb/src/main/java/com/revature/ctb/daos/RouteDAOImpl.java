package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.Route;

@Repository
public class RouteDAOImpl implements RouteDAO {

	private SessionFactory sessionFactory;
	
	@Autowired
	public void SessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Route getRouteById(Integer routeId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(Route.class, routeId);
	}

	@Override
	public boolean addRoute(Route route) {
		Session session = sessionFactory.getCurrentSession();

		session.save(route);

		return route.getRouteId() > 0;
	}

	@Override
	public boolean deleteRoute(Integer routeId) {
		Session session = sessionFactory.getCurrentSession();

		String hql = "delete from route where routeid = :routeid";

		Query<Route> query = session.createQuery(hql, Route.class);
		query.setParameter("routeid", routeId);

		return query.executeUpdate() > 0;
	}

	@Override
	public List<Route> getAllRoutesByRideId(Integer rideId) {
		Session session = sessionFactory.getCurrentSession();

		Ride ride = session.get(Ride.class, rideId);

		return ride.getRoutes();
	}

}
