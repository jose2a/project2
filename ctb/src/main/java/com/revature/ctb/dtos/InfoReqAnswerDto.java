package com.revature.ctb.dtos;

import javax.validation.constraints.NotEmpty;

public class InfoReqAnswerDto {

	private Integer inforeqId;
	
	@NotEmpty(message = "Answer is required") // Validating
	private String answer;

	public Integer getInforeqId() {
		return inforeqId;
	}

	public void setInforeqId(Integer inforeqId) {
		this.inforeqId = inforeqId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
