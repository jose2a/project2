package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.daos.RouteDAO;
import com.revature.ctb.domains.Route;

public class RouteServiceImpl implements RouteService {

	private RouteDAO routeDao;

	public void setRouteDao(RouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	@Override
	public boolean addRoutes(List<Route> routes) {		
		for(Route r: routes) {
			routeDao.addRoute(r);
		}

		return true;

	}

	@Override
	public boolean deleteRoutes(Integer rideId) {

		List<Route> oldRoutes = routeDao.getAllRoutesByRideId(rideId);

		for(Route r: oldRoutes) {
			routeDao.deleteRoute(r.getRouteId());
		}
		
		return true;

	}

	@Override
	public List<Route> getRoutesByRideId(Integer rideId) {

		
		return null;
	}

}
