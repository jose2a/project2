package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Route;

public interface RouteDAO {
	
	public Route getRoute (Integer routeid);
	
	public boolean addRoute (Route route);
	
	public boolean deleteRoute(Integer routeid); 
	
	public List<Route> getAllRoutes(Integer rideId);
	
	// public Route updateRoute(Integer routeid);

}
