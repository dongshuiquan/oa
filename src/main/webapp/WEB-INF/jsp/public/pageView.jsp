<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id=PageSelectorBar>
	<div id=PageSelectorMemo>
		页次：${currentPage }/${pageCount}页 &nbsp;
		每页显示：${pageSize }条 &nbsp;
		总记录数：${recordCount }条
	</div>
	<div id=PageSelectorSelectorArea>
	
		<a href="javascript:gotoPage(1)" title="首页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/firstPage.png"/>
		</a>
		<s:iterator begin="%{beginPageIndex}" end="%{endPageIndex}" var="num">
			<s:if test="currentPage == #num">	<!-- 当前页 -->
				<span class="PageSelectorNum PageSelectorSelected">${num}</span>
			</s:if>
			<s:else>	<!-- 非当前页 -->
				<span class="PageSelectorNum" style="cursor: hand;" onClick="gotoPage(${num});">${num}</span>
			</s:else>
		</s:iterator>
		
		<a href="javascript:gotoPage(${num })" title="尾页" style="cursor: hand;">
			<img src="${pageContext.request.contextPath}/style/blue/images/pageSelector/lastPage.png"/>
		</a>
		
		转到：
		<select onchange="gotoPage(this.value)">
			<s:iterator begin="1" end="%{pageCount}" var="num">
				<option value="${num}">${num}</option>
			</s:iterator>
		</select>
	</div>
</div>

