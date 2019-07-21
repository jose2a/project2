package com.revature.ctb.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ctb.domains.FeedbackType;
import com.revature.ctb.services.FeedbackTypeService;

@RestController
public class FeedbackRestController extends BasedRestController {

	private FeedbackTypeService feedbackTypeService;

	@Autowired
	public void setFeedbackTypeService(FeedbackTypeService feedbackTypeService) {
		this.feedbackTypeService = feedbackTypeService;
	}

	@GetMapping(value = "feedback/{feedbackTypeId}")
	@ResponseStatus(code = HttpStatus.OK)
	public FeedbackType geFeedbackType(@PathVariable Integer feedbackTypeId) {
		return feedbackTypeService.geFeedbackType(feedbackTypeId);
	}

	@GetMapping(value = "feedback")
	@ResponseStatus(code = HttpStatus.OK)
	public List<FeedbackType> getAllFeedbackTypes() {
		return feedbackTypeService.getAllFeedbackTypes();
	}

}
