package com.revature.ctb.daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.domains.FeedbackType;

public class FeedbackTypeDAOImpl implements FeedbackTypeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FeedbackType geFeedbackType(Integer feedbackTypeId) {
		Session session = sessionFactory.openSession();

		return session.get(FeedbackType.class, feedbackTypeId);
	}

}
