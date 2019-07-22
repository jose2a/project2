package com.revature.ctb.daos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.revature.ctb.domains.RideStatus;
import com.revature.ctb.domains.RideStatus.RideStatusIds;
import com.revature.ctb.utils.SessionFactoryUtil;

public class RideStatusDAOImplTest {

	private RideStatusDAOImpl rideStatusDao;

//	@Before
	public void setUp() throws Exception {
		rideStatusDao = new RideStatusDAOImpl();
		rideStatusDao.setSessionFactory(SessionFactoryUtil.getSessionFactory());
	}

//	@Test
	public void testGetRideStatus() {
		RideStatus rideStatus = rideStatusDao.getRideStatus(RideStatusIds.ACTIVE);

		assertEquals("Active", rideStatus.getName());
	}

}
