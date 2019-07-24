package com.revature.ctb.dtos;

public class RouteDto {
	private Integer routeId;

	private String name;

	private String address;

	private String latitude;

	private String longitude;

	private boolean pickupLocation;

	private boolean destinationLocation;

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(boolean pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public boolean isDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(boolean destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
}
