package com.revature.ctb.dtos;

import java.util.ArrayList;
import java.util.List;

public class RideAndRoutesDto extends RideDto {
	
	protected List<RouteDto> routes = new ArrayList<>();

	public List<RouteDto> getRoutes() {
		return routes;
	}

	public void setRoutes(List<RouteDto> routes) {
		this.routes = routes;
	}

	public void addRoute(RouteDto routeDto) {
		this.routes.add(routeDto);
	}
}
