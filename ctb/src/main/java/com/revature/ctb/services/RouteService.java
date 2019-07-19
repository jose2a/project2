package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.Route;


public interface RouteService {
	
	public boolean addRoutes(List<Route> routes);
	
	public boolean deleteRoutes(Integer rideId);
	
	public List<Route> getRoutesByRideId(Integer rideId);

}
