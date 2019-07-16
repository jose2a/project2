package com.revature.ctb.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_req")
public class InfoReq {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "inforeq_id")
	private Integer inforeqId;

	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

	public InfoReq() {
	}

	public InfoReq(Integer inforeqId, String question, String answer) {
		super();
		this.inforeqId = inforeqId;
		this.question = question;
		this.answer = answer;
	}

	public InfoReq(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((inforeqId == null) ? 0 : inforeqId.hashCode());
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
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InfoReq [inforeqId=" + inforeqId + ", question=" + question + ", answer=" + answer + "]";
	}

}
