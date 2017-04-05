package com.oa.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Reply extends Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Topic topic;

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
