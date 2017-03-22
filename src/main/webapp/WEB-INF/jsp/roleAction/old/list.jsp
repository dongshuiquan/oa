<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<body>
	 <!--
	<s:iterator value="#roleList">
		<s:property value="id"/>
		<s:property value="name"/>
		<s:property value="description"/>
		<a href="roleAction_delete.action?id=<s:property value='id'/>" onclick="return confirm('确定要删除吗？')">删除</a><br/>
	</s:iterator>
	 -->
	<s:iterator value="#roleList">
		${id }
		${name }
		${description }
<%-- 		<a href="${pageContext.request.contextPath}/roleAction_delete.action?id=${id }" onclick="return confirm('确定要删除吗？')">删除</a><br/> --%>
		<s:a action="roleAction_delete.action" onclick="return confirm('确定要删除吗？')">
			<s:param name="id" value="%{id}"/>
			删除
		</s:a>
		<s:a action="roleAction_editUI.action?id=%{id}">修改</s:a>
		<br/>
	</s:iterator><br /><br />
	<s:a action="roleAction_addUI.action">添加岗位</s:a>
<!-- 	<a href="/roleAction_addUI.action">添加岗位</a> -->
</body>
</html>