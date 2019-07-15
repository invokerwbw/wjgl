<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>标准更新</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/jsp/wjgl/bzgx.js"></script>
<style type="text/css">
.importFile {
	position: absolute;
	font-size: 30px;
	right: 0;
	top: 0;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer
}
</style>
</head>
<body>
	<div class="container">
		<div id="outer" class="row">
			<div style="height: 60px;"></div>
			<div style="height: 20px;"></div>
			<form id="excelForm" name="excelForm" onsubmit="return false;"
				enctype="multipart/form-data">

				<div class="input-group">
					<input class="form-control ue-form" type="text" id="showInputFile"
						disabled="disabled">
					<div class="input-group-addon ue-form-btn">
						<span class="fa fa-upload"><a href="javascript:;"><input
								type="file" name="importFile" id="importFile" class="importFile" /></a></span>
					</div>
				</div>
			</form>
			<div style="height: 50px;"></div>
			<div style="text-align: right;">
				<button id="uploadBtn" class="btn ue-btn-primary">上传</button>
			</div>
		</div>
	</div>
</body>
</html>