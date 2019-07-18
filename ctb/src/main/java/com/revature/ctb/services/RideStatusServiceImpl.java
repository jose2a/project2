package com.revature.ctb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.RideStatusDAO;
import com.revature.ctb.domains.RideStatus;

public class RideStatusServiceImpl implements RideStatusService {

	private RideStatusDAO rideStatusDao;

	@Autowired
	public void setRideStatusDao(RideStatusDAO rideStatusDao) {
		this.rideStatusDao = rideStatusDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public RideStatus getRideStatus(Integer rideStatusId) {
		return rideStatusDao.getRideStatus(rideStatusId);
	}

}
