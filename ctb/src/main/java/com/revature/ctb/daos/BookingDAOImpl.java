package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.Booking;
import com.revature.ctb.domains.Ride;

@Repository
public class BookingDAOImpl implements BookingDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addBooking(Booking booking) {
		Session session = sessionFactory.getCurrentSession();

		session.save(booking);

		return booking.getBookingId() > 0;
	}

	@Override
	public boolean updateBooking(Booking booking) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(booking);

		return true;
	}

	@Override
	public boolean deleteBooking(Integer bookingId) {
		Session session = sessionFactory.getCurrentSession();

		Query<Booking> query = session.createQuery("delete from Booking where bookingId = :bookingId", Booking.class);
		query.setParameter("bookingId", bookingId);

		return query.executeUpdate() > 0;
	}

	@Override
	public List<Booking> getBookingsByRideId(Integer rideId) {
		Session session = sessionFactory.getCurrentSession();

		Ride ride = session.get(Ride.class, rideId);

		return ride.getBookings();
	}

	@Override
	public List<Booking> getAllBooking() {
		Session session = sessionFactory.getCurrentSession();

		Query<Booking> query = session.createQuery("from Booking", Booking.class);

		return query.getResultList();
	}
	
	

}
