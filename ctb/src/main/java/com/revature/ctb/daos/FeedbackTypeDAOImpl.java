package com.revature.ctb.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.ctb.domains.FeedbackType;

@Repository
public class FeedbackTypeDAOImpl implements FeedbackTypeDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public FeedbackType getFeedbackType(Integer feedbackTypeId) {
		Session session = sessionFactory.getCurrentSession();

		return session.get(FeedbackType.class, feedbackTypeId);
	}

	@Override
	public List<FeedbackType> getAllFeedbackTypes() {
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery("from FeedbackType", FeedbackType.class).getResultList();
	}

}
