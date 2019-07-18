package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.Route;

public interface RouteDAO {

	public Route getRouteById(Integer routeid);

	public boolean addRoute(Route route);

	public boolean deleteRoute(Integer routeid);

	public List<Route> getAllRoutesByRideId(Integer rideId);

}
