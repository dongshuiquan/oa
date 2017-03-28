package com.oa.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.oa.domain.Forum;
import com.oa.domain.PageBean;
import com.oa.domain.Topic;
import com.oa.util.HqlHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumAction extends BaseAction<Forum>{

	private static final long serialVersionUID = 1L;
	/**
	 * 0 表示全部主题<br>
	 * 1 表示只看精华贴
	 */
	private int viewType = 0;
	/**
	 * 0 表示默认排序（所有置顶贴在前面， 并且按最后更新时间排序;<br>
	 * 1 代表只按最后更新时间排序<br>
	 * 2 代表只按主题发表时间排序<br>
	 * 3 代表只按回复数量排序
	 */
	private int orderBy = 0;
	/**
	 * true 表示升序<br>
	 * false 表示降序
	 */
	private boolean asc = false;
	/**显示版块列表*/
	public String list() throws Exception{
		List<Forum> forumList = forumService.findAll();
		ActionContext.getContext().put("forumList", forumList);
		return "list";
	}
	
	/**显示单个版块(主题列表）*/
	public String show() throws Exception{
		//准备数据 forum
		Forum forum = forumService.getById(model.getId());
		ActionContext.getContext().put("forum", forum);
		//准备数据 topicList
//		List<Topic> topicList = topicService.findByForum(forum);
//		ActionContext.getContext().put("topicList", topicList);
		
		//准备主题列表的分页信息
//		PageBean pageBean = topicService.getPageBean(pageNum, forum);
//		ActionContext.getContext().getValueStack().push(pageBean);
		//准备主题列表的分页信息
//		String hql = "FROM Topic t WHERE t.forum = ? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,"
//				+ " t.lastUpdateTime DESC";
//		Object[] parameters = new Object[]{forum};
		
		HqlHelper hqlHelper = new HqlHelper(Topic.class, "t")//
				.addCondition("t.forum=?", forum)//
				.addCondition(viewType == 1, "t.type=?", Topic.TYPE_BEST) // 1表示只看精华帖
				.addOrder(orderBy == 1, "t.lastUpdateTime", asc) // 1 代表只按最后更新时间排序
				.addOrder(orderBy == 2, "t.postTime", asc) // 2 代表只按主题发表时间排序
				.addOrder(orderBy == 3, "t.replyCount", asc) // 3 代表只按回复数量排序
				.addOrder(orderBy == 0, "(CASE t.type WHEN 2 THEN 2 ELSE 0 END)", false)//
				.addOrder(orderBy == 0, "t.lastUpdateTime", false); // 0 代表默认排序(所有置顶帖在前面，并按最后更新时间降序排列)
		
		PageBean pageBean = replyService.getPageBean(pageNum, hqlHelper);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "show";
	}

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}
	
}
