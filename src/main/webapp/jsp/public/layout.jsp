<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
</head>
<body>
	<header>
		<img
			src="${pageContext.request.contextPath}/skins/skin/img/inspur.jpg" />
	</header>
	<hr/>
	<!-- 页面内容 -->
	<sitemesh:write property='body' />
	<hr />
	<footer>footer</footer>
</body>
</html>