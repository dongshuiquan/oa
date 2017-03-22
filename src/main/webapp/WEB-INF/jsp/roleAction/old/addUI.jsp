<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addUI</title>
</head>
<body>
	<s:form action="roleAction_add.action">
		名称 : <s:textfield name="name"></s:textfield><br /><br />
		说明 : <s:textarea name="description" rows="5" cols="35"></s:textarea><br /><br />
		<s:submit value="提交"></s:submit>
	</s:form>

</body>
</html>