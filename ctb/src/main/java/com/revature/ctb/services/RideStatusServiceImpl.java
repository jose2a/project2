package com.revature.ctb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ctb.daos.RideStatusDAO;
import com.revature.ctb.domains.RideStatus;

@Service
public class RideStatusServiceImpl implements RideStatusService {

	private RideStatusDAO rideStatusDao;

	@Autowired
	public void setRideStatusDao(RideStatusDAO rideStatusDao) {
		this.rideStatusDao = rideStatusDao;
	}

	@Override
	public RideStatus getRideStatus(Integer rideStatusId) {
		return rideStatusDao.getRideStatus(rideStatusId);
	}

}
