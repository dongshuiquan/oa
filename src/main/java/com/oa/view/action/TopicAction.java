package com.oa.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Reply;
import com.oa.domain.Topic;
import com.oa.domain.User;
import com.oa.util.HqlHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TopicAction extends ModelDrivenBaseAction<Topic>{

	private static final long serialVersionUID = 1L;
	private Long forumId;
	
	/**显示单个主题(主题 + 回帖）*/
	public String show() throws Exception{
		//准备数据
		Topic topic = topicService.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		//准备数据
//		List<Reply> replyList = replyService.getByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		//准备数据(使用公有方法)
//		String hql = "FROM Reply r WHERE r.topic = ? ORDER BY r.postTime ASC";
//		Object[] parameters = new Object[]{topic};
//		PageBean pageBean = replyService.getPageBean(pageNum, hql, parameters);
		HqlHelper hqlHelper = new HqlHelper(Reply.class, "r");
		hqlHelper.addCondition("r.topic=?", topic)
				.addOrder("r.postTime", true);
		PageBean pageBean = replyService.getPageBean(pageNum, hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	/**发表新主题页面*/
	public String addUI() throws Exception{
		Forum forum = forumService.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}
	
	/**增加新主题*/
	public String add() throws Exception{
		//封装 ： 
		// 表单中的字段 ， 已经有 title, content, faceIcon
		model.setForum(forumService.getById(forumId));
		
		//当前可以直接获取的信息
		User user = getCurrentUser();
		model.setAuthor(user);
		model.setIdAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		// 业务层的字段  type, replyCount, lastReply, lastUpdateTime
		topicService.save(model);
		return "toShow"; //转到新主题的显示页面
	}
	
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	
	
}
