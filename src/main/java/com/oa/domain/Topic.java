package com.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
public class Topic extends Article{
	/**普通贴*/
	public static final int TYPE_NORMAL = 0;
	/**精华贴*/
	public static final int TYPE_BEST = 1;
	/**置顶贴*/
	public static final int TYPE_TOP = 2;
	@ManyToOne
	private Forum forum;
	@OneToMany(mappedBy="topic")
	private Set<Reply> reply = new HashSet<>();
	private int type;
	private int replyCount;
	@OneToOne
	private Reply lastReply;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTime;//发表主题或最后回复时间 
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Set<Reply> getReply() {
		return reply;
	}
	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
