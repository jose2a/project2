package com.revature.ctb.domains;

public class Car {

	private Integer carId;

	private String vinNumber;
	private String make;
	private String model;
	private int numberOfSeats;
	private boolean ac;

	public Car() {
		// TODO Auto-generated constructor stub
	}

	public Car(Integer carId, String vinNumber, String make, String model, int numberOfSeats, boolean ac) {
		super();
		this.carId = carId;
		this.vinNumber = vinNumber;
		this.make = make;
		this.model = model;
		this.numberOfSeats = numberOfSeats;
		this.ac = ac;
	}

	public Car(String vinNumber, String make, String model, int numberOfSeats, boolean ac) {
		super();
		this.vinNumber = vinNumber;
		this.make = make;
		this.model = model;
		this.numberOfSeats = numberOfSeats;
		this.ac = ac;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(String vinNumber) {
		this.vinNumber = vinNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ac ? 1231 : 1237);
		result = prime * result + ((carId == null) ? 0 : carId.hashCode());
		result = prime * result + ((make == null) ? 0 : make.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + numberOfSeats;
		result = prime * result + ((vinNumber == null) ? 0 : vinNumber.hashCode());
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
		Car other = (Car) obj;
		if (ac != other.ac)
			return false;
		if (carId == null) {
			if (other.carId != null)
				return false;
		} else if (!carId.equals(other.carId))
			return false;
		if (make == null) {
			if (other.make != null)
				return false;
		} else if (!make.equals(other.make))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (vinNumber == null) {
			if (other.vinNumber != null)
				return false;
		} else if (!vinNumber.equals(other.vinNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", vinNumber=" + vinNumber + ", make=" + make + ", model=" + model
				+ ", numberOfSeats=" + numberOfSeats + ", ac=" + ac + "]";
	}

}
