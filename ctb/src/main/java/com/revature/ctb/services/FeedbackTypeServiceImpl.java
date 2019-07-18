package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.ctb.daos.FeedbackTypeDAO;
import com.revature.ctb.domains.FeedbackType;

public class FeedbackTypeServiceImpl implements FeedbackTypeService {

	private FeedbackTypeDAO feedbackTypeDAO;

	@Autowired
	public void setFeedbackTypeDAO(FeedbackTypeDAO feedbackTypeDAO) {
		this.feedbackTypeDAO = feedbackTypeDAO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public FeedbackType geFeedbackType(Integer feedbackTypeId) {
		return feedbackTypeDAO.getFeedbackType(feedbackTypeId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public List<FeedbackType> getAllFeedbackTypes() {
		return feedbackTypeDAO.getAllFeedbackTypes();
	}

}
