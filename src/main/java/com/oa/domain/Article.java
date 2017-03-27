package com.oa.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 文章
 * @author dong
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String content;
	private String faceIcon;
	@ManyToOne
	private User author;
	@Temporal(TemporalType.TIMESTAMP)
	private Date posetTime;
	private String idAddr;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFaceIcon() {
		return faceIcon;
	}
	public void setFaceIcon(String faceIcon) {
		this.faceIcon = faceIcon;
	}
	public Date getPosetTime() {
		return posetTime;
	}
	public void setPosetTime(Date posetTime) {
		this.posetTime = posetTime;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getIdAddr() {
		return idAddr;
	}
	public void setIdAddr(String idAddr) {
		this.idAddr = idAddr;
	}
	
}
