package com.revature.ctb.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_type")
public class FeedbackType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedbacktype_id")
	private Integer feedbackTypeId;

	@Column(name = "name")
	private String name;

	public FeedbackType() {
	}

	public FeedbackType(Integer feedbackTypeId, String name) {
		super();
		this.feedbackTypeId = feedbackTypeId;
		this.name = name;
	}

	public FeedbackType(String name) {
		super();
		this.name = name;
	}

	public Integer getFeedbackTypeId() {
		return feedbackTypeId;
	}

	public void setFeedbackTypeId(Integer feedbackTypeId) {
		this.feedbackTypeId = feedbackTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((feedbackTypeId == null) ? 0 : feedbackTypeId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		FeedbackType other = (FeedbackType) obj;
		if (feedbackTypeId == null) {
			if (other.feedbackTypeId != null)
				return false;
		} else if (!feedbackTypeId.equals(other.feedbackTypeId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FeedbackType [feedbackTypeId=" + feedbackTypeId + ", name=" + name + "]";
	}

	public static class FeedbackTypeId {
		public static final Integer ONE_STAR = 1;
		public static final Integer TWO_STAR = 2;
		public static final Integer THREE_STAR = 3;
		public static final Integer FOUR_STAR = 4;
		public static final Integer FIVE_STAR = 5;
	}

}
