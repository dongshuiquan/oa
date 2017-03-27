package com.oa.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Reply extends Article{
	@ManyToOne
	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
