package com.revature.ctb.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ride_status")
public class RideStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ridestatus_id")
	private Integer rideStatusId;

	@Column(name = "name")
	private String name;

	public RideStatus() {
	}

	public RideStatus(Integer rideStatusId, String name) {
		super();
		this.rideStatusId = rideStatusId;
		this.name = name;
	}

	public RideStatus(String name) {
		super();
		this.name = name;
	}

	public Integer getRideStatusId() {
		return rideStatusId;
	}

	public void setRideStatusId(Integer rideStatusId) {
		this.rideStatusId = rideStatusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rideStatusId == null) ? 0 : rideStatusId.hashCode());
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
		RideStatus other = (RideStatus) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (rideStatusId == null) {
			if (other.rideStatusId != null)
				return false;
		} else if (!rideStatusId.equals(other.rideStatusId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RideStatus [rideStatusId=" + rideStatusId + ", name=" + name + "]";
	}
	
	public static class RideStatusIds {
		public static final Integer ACTIVE = 1;
		public static final Integer COMPLETED = 2;
		public static final Integer CANCELED = 3;
	}

}
