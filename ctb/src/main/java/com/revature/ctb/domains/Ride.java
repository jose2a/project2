package com.revature.ctb.domains;

import java.sql.Date;
import java.sql.Time;

public class Ride {

	private Integer rideId;

	private Date departureDate;
	private Time departureTime;

	private int numberOfSeatsAvailable;
	private double amountCharge;

	private RideStatus rideStatus;

	public Ride() {
		// TODO Auto-generated constructor stub
	}

	public Ride(Integer rideId, Date departureDate, Time departureTime, int numberOfSeatsAvailable, double amountCharge,
			RideStatus rideStatus) {
		super();
		this.rideId = rideId;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
		this.amountCharge = amountCharge;
		this.rideStatus = rideStatus;
	}

	public Ride(Date departureDate, Time departureTime, int numberOfSeatsAvailable, double amountCharge,
			RideStatus rideStatus) {
		super();
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
		this.amountCharge = amountCharge;
		this.rideStatus = rideStatus;
	}

	public Integer getRideId() {
		return rideId;
	}

	public void setRideId(Integer rideId) {
		this.rideId = rideId;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Time departureTime) {
		this.departureTime = departureTime;
	}

	public int getNumberOfSeatsAvailable() {
		return numberOfSeatsAvailable;
	}

	public void setNumberOfSeatsAvailable(int numberOfSeatsAvailable) {
		this.numberOfSeatsAvailable = numberOfSeatsAvailable;
	}

	public double getAmountCharge() {
		return amountCharge;
	}

	public void setAmountCharge(double amountCharge) {
		this.amountCharge = amountCharge;
	}

	public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amountCharge);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((departureDate == null) ? 0 : departureDate.hashCode());
		result = prime * result + ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + numberOfSeatsAvailable;
		result = prime * result + ((rideId == null) ? 0 : rideId.hashCode());
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
		Ride other = (Ride) obj;
		if (Double.doubleToLongBits(amountCharge) != Double.doubleToLongBits(other.amountCharge))
			return false;
		if (departureDate == null) {
			if (other.departureDate != null)
				return false;
		} else if (!departureDate.equals(other.departureDate))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (numberOfSeatsAvailable != other.numberOfSeatsAvailable)
			return false;
		if (rideId == null) {
			if (other.rideId != null)
				return false;
		} else if (!rideId.equals(other.rideId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ride [rideId=" + rideId + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", numberOfSeatsAvailable=" + numberOfSeatsAvailable + ", amountCharge=" + amountCharge
				+ ", rideStatus=" + rideStatus + "]";
	}

}
