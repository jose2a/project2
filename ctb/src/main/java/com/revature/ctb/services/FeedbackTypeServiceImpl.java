package com.revature.ctb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.ctb.daos.FeedbackTypeDAO;
import com.revature.ctb.domains.FeedbackType;

public class FeedbackTypeServiceImpl implements FeedbackTypeService {

	private FeedbackTypeDAO feedbackTypeDAO;

	@Autowired
	public void setFeedbackTypeDAO(FeedbackTypeDAO feedbackTypeDAO) {
		this.feedbackTypeDAO = feedbackTypeDAO;
	}

	@Override
	public FeedbackType geFeedbackType(Integer feedbackTypeId) {
		return feedbackTypeDAO.getFeedbackType(feedbackTypeId);
	}

	@Override
	public List<FeedbackType> getAllFeedbackTypes() {
		return feedbackTypeDAO.getAllFeedbackTypes();
	}

}
