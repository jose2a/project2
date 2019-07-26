package com.revature.ctb.dtos;

public class InfoReqDto {

	private Integer inforeqId;

	private String question;

	private String answer;

	private boolean provided;

	private EmployeeDto employee;

	public Integer getInforeqId() {
		return inforeqId;
	}

	public void setInforeqId(Integer inforeqId) {
		this.inforeqId = inforeqId;
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

	public boolean isProvided() {
		return provided;
	}

	public void setProvided(boolean provided) {
		this.provided = provided;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

}
