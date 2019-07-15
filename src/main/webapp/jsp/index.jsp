<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准查询系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript">
	function init() {
		window.location.href = ctx + "/service/sys/welcome";
	}
</script>
</head>
<body onload="init();">
</body>
</html>