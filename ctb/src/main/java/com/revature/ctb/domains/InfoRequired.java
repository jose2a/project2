package com.revature.ctb.domains;

public class InfoRequired {

	private Integer infoRequiredId;

	private String question;
	private String answer;

	public InfoRequired() {
		// TODO Auto-generated constructor stub
	}

	public InfoRequired(Integer infoRequiredId, String question, String answer) {
		super();
		this.infoRequiredId = infoRequiredId;
		this.question = question;
		this.answer = answer;
	}

	public InfoRequired(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
	}

	public Integer getInfoRequiredId() {
		return infoRequiredId;
	}

	public void setInfoRequiredId(Integer infoRequiredId) {
		this.infoRequiredId = infoRequiredId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((infoRequiredId == null) ? 0 : infoRequiredId.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoRequired other = (InfoRequired) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (infoRequiredId == null) {
			if (other.infoRequiredId != null)
				return false;
		} else if (!infoRequiredId.equals(other.infoRequiredId))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InfoRequired [infoRequiredId=" + infoRequiredId + ", question=" + question + ", answer=" + answer + "]";
	}

}
