package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.FeedbackType;

public class FeedbackTypeDAOImpl implements FeedbackTypeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FeedbackType getFeedbackType(Integer feedbackTypeId) {
		Session session = sessionFactory.openSession();

		return session.get(FeedbackType.class, feedbackTypeId);
	}

	@Override
	public List<FeedbackType> getAllFeedbackTypes() {
		Session sess = sessionFactory.openSession();

		Query<FeedbackType> query = sess.createQuery("from FeedbackType", FeedbackType.class);

		return query.getResultList();
	}

}
