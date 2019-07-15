<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="/tags/loushang-web" prefix="l"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/skins/wjgl/user.js"></script>
<title>添加用户</title>
</head>
<body>
	<input type="text" id="userAccount" hidden="true" name="btn_name"
		value="${model.account}"></input>
	<form name="userForm" id="userForm" class="form-horizontal"
		method="post" onsubmit="return false">

		<div class="form-group">
			<label class="col-xs-3 control-label" for="account">账号</label>
			<div class="col-xs-7">
				<input id="account" name="account" value="${model.account}"
					class="form-control" type="text" disabled>
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="password">密码</label>
			<div class="col-xs-7">
				<input id="password" name="password" value="${model.password}"
					class="form-control" type="password">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="xingming">姓名</label>
			<div class="col-xs-7">
				<input id="xingming" name="xingming" value="${model.name}"
					class="form-control" type="text">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="bumen">所在部门</label>
			<div class="col-xs-7">
				<input id="bumen" name="bumen" value="${model.department}"
					class="form-control" type="text">
			</div>
		</div>
		<div class="form-group">
			<label class="col-xs-3 control-label" for="beizhu">备注</label>
			<div class="col-xs-7">
				<input id="beizhu" name="beizhu" value="${model.mark}"
					class="form-control" type="text">
			</div>
		</div>
		<div align="center">
			<button class="btn ue-btn" type="button" onclick="userSave()">
				<span class="fa fa-search">保 存</span>
			</button>
		</div>
	</form>
	<script type="text/javascript">
		var editFlag = $("#userAccount").val();
		if (editFlag == null || editFlag == "") {
			$("#account").attr("disabled", false);
		}
	</script>

</body>
</html>