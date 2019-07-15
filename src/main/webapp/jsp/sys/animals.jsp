<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>animals</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<l:script path="echarts/echarts.min.js" />
<script type="text/javascript" src="${ctx}/jsp/sys/animals.js"></script>
</head>
<body>
	<div class="container">
		<div id="chartAnimals" style="height: 500px;"></div>
	</div>
</body>
</html>