package com.revature.ctb.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "route")
public class Route {

	@Id
	@Column(name = "route_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer routeId;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	@Column(name = "pickup_loc")
	private boolean pickupLocation;

	@Column(name = "destination_loc")
	private boolean destinationLocation;

	// Many routes are in one ride
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "ride_id")
	private Ride ride;

	// one route can be a pickup location in many bookings
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pickupLocation")
	private List<Booking> pickupLocations = new ArrayList<>();

	// one route can be a destination location in many bookings
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "destinationLocation")
	private List<Booking> destinationLocations = new ArrayList<>();

	public Route() {
		super();
	}

	public Route(Integer routeId, String name, String address, String latitude, String longitude,
			boolean pickupLocation, boolean destinationLocation, Ride ride) {
		super();
		this.routeId = routeId;
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pickupLocation = pickupLocation;
		this.destinationLocation = destinationLocation;
		this.ride = ride;
	}

	public Route(String name, String address, String latitude, String longitude, boolean pickupLocation,
			boolean destinationLocation, Ride ride) {
		super();
		this.name = name;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.pickupLocation = pickupLocation;
		this.destinationLocation = destinationLocation;
		this.ride = ride;
	}

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

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public List<Booking> getPickupLocations() {
		return pickupLocations;
	}

	public void setPickupLocations(List<Booking> pickupLocations) {
		this.pickupLocations = pickupLocations;
	}

	public List<Booking> getDestinationLocations() {
		return destinationLocations;
	}

	public void setDestinationLocations(List<Booking> destinationLocations) {
		this.destinationLocations = destinationLocations;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (destinationLocation ? 1231 : 1237);
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (pickupLocation ? 1231 : 1237);
		result = prime * result + ((routeId == null) ? 0 : routeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (destinationLocation != other.destinationLocation)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pickupLocation != other.pickupLocation)
			return false;
		if (routeId == null) {
			if (other.routeId != null)
				return false;
		} else if (!routeId.equals(other.routeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", name=" + name + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", pickupLocation=" + pickupLocation + ", destinationLocation="
				+ destinationLocation + "]";
	}
}