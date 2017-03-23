<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>您已退出 OA系统</title>
     <%@ include file="/WEB-INF/jsp/public/common.jsp" %>
	<link href="${pageContext.request.contextPath}/style/blue/logout.css" rel="stylesheet" type="text/css">
</head>

<body>
	<table width="100%" height="100%" >
		<tr>
			<td align=center>
				<div id=Logout>
					<div id=AwokeMsg>
                        <img id=LogoutImg src="${pageContext.request.contextPath}/style/blue/images/logout/logout.gif" border=0>
                        <img id=LogoutTitle src="${pageContext.request.contextPath}/style/blue/images/logout/logout1.gif" border=0>
                    </div>
					<div id=LogoutOperate>
                        <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout2.gif" border=0> 
                        <a href="${pageContext.request.contextPath}/userAction_loginUI.action">重新进入系统</a> 
                        <img src="${pageContext.request.contextPath}/style/blue/images/logout/logout3.gif" border=0> 
                        <a href="javascript: window.open('','_self');window.close();">关闭当前窗口</a>
                    </div>
				</div>
			</td>
		</tr>
	</table>
</body>
</html>
