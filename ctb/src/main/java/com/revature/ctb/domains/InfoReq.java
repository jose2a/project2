package com.revature.ctb.domains;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "info_req")
public class InfoReq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inforeq_id")
	private Integer inforeqId;

	@Column(name = "question")
	@NotEmpty(message = "Question is required") // Validating
	private String question;

	@Column(name = "answer")
	private String answer;

	@Column(name = "provided")
	private boolean provided;

	// Employee has many requests for information
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinColumn(name = "employee_id")
	private Employee employee;

	public InfoReq() {
	}

	public InfoReq(String question, String answer, boolean provided, Employee employee) {
		super();
		this.question = question;
		this.answer = answer;
		this.provided = provided;
		this.employee = employee;
	}

	public InfoReq(Integer inforeqId, String question, String answer, boolean provided, Employee employee) {
		super();
		this.inforeqId = inforeqId;
		this.question = question;
		this.answer = answer;
		this.provided = provided;
		this.employee = employee;
	}

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((inforeqId == null) ? 0 : inforeqId.hashCode());
		result = prime * result + (provided ? 1231 : 1237);
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
		InfoReq other = (InfoReq) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (inforeqId == null) {
			if (other.inforeqId != null)
				return false;
		} else if (!inforeqId.equals(other.inforeqId))
			return false;
		if (provided != other.provided)
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
		return "InfoReq [inforeqId=" + inforeqId + ", question=" + question + ", answer=" + answer + ", provided="
				+ provided + ", employee=" + employee + "]";
	}

}
