package com.revature.ctb.daos;

import java.util.List;

import com.revature.ctb.domains.FeedbackType;

public interface FeedbackTypeDAO {
	public FeedbackType getFeedbackType(Integer feedbackTypeId);
	
	public List<FeedbackType> getAllFeedbackTypes();
}
