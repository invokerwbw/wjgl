<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准维护</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/jsp/modify/pdfUpload.js"></script>
</head>
<body>
	<div class="container" style="border: 1px solid #EFEEEE;">
		<input id="bzh" name="bzh" type="hidden" value="${bzh}" />
		<form action="" method="post"> 
        	<a class="btn ue-btn" id="rpickfiles">点击上传标准PDF文件</a>
    	</form>
    	<div id="rresult"></div>
	</div>
</body>
</html>