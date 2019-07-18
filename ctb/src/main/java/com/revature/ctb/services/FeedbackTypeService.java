package com.revature.ctb.services;

import java.util.List;

import com.revature.ctb.domains.FeedbackType;

public interface FeedbackTypeService {

	public FeedbackType geFeedbackType(Integer feedbackTypeId);

	public List<FeedbackType> getAllFeedbackTypes();
}
