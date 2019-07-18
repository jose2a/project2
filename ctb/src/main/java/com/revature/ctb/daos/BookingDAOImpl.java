package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;
import com.revature.ctb.utils.LogUtil;

public class BookingDAOImpl implements BookingDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addBooking(Booking booking) {
		LogUtil.trace("BookingDAOImpl - addBooking");

		Session session = sessionFactory.openSession();

		session.save(booking);

		return booking.getBookingId() > 0;
	}

	@Override
	public boolean updateBooking(Booking booking) {
		LogUtil.trace("BookingDaoImpl - updateBooking");

		Session session = sessionFactory.openSession();

		session.saveOrUpdate(booking);

		return true;
	}

	@Override
	public boolean deleteBooking(Booking bookingId) {
		LogUtil.trace("BookingDAOImpl - deleteBooking");

		Session session = sessionFactory.openSession();

		Query<Booking> query = session.createQuery("delete from Booking where bookingId = :bookingId", Booking.class);
		query.setParameter("bookingId", bookingId);

		return query.executeUpdate() > 0;
	}

	@Override
	public List<Booking> getBookingsByRideId(Integer rideId) {
		LogUtil.trace("BookingDAOImpl - Get Booking by rideId");

		Session session = sessionFactory.openSession();

		Ride ride = session.get(Ride.class, rideId);

		return ride.getBookings();
	}

}
