<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>editUI</title>
</head>
<body>
	<s:form action="roleAction_edit.action">
		<s:hidden name="id" value="%{model.id}"></s:hidden>
		名称 : <s:textfield name="name" value="%{model.name}"></s:textfield><br /><br />
		说明 : <s:textarea name="description" rows="5" cols="35" value="%{model.description}"></s:textarea><br /><br />
		<s:submit value="修改"></s:submit>
	</s:form>
	
	<s:debug></s:debug>
</body>
</html>