package com.revature.ctb.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.revature.ctb.domains.Ride;
import com.revature.ctb.domains.Route;
import com.revature.ctb.utils.LogUtil;

public class RouteDAOImpl implements RouteDAO {
	
	private SessionFactory sessionFactory; 

	@Override
	public Route getRoute(Integer routeId) {

		LogUtil.trace("RouteDAOImpl - getRoute");
		Session session = sessionFactory.openSession();
		
		Route route = null;
		
		route = session.get(Route.class, routeId);
		
		return route;
	}

	@Override
	public boolean addRoute(Route route) {
		LogUtil.trace("RouteDAOImpl - addRoute");
		Session session = sessionFactory.openSession();
		
		session.save(route);
		
		
		return route.getRouteId() > 0;
	}

	@Override
	public boolean deleteRoute(Integer routeId) {
		
		LogUtil.trace("RouteDAOImpl - deleteRoute");
		Session session = sessionFactory.openSession();
		
		String hql = "delete from route where routeid = :routeid";
		Query query = session.createQuery(hql);
		query.setParameter("routeid", routeId);
		Integer result = query.executeUpdate();
		
		return result > 0;
	}

	@Override
	public List<Route> getAllRoutes(Integer rideId) {
		
		LogUtil.trace("RouteDAOImpl - getAllRoutes");
		Session session = sessionFactory.openSession();
		
		Ride ride = session.get(Ride.class, rideId);
		
		
		return ride.getRoutes();
	}

}
