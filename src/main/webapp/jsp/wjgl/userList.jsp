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
<style>
body {
	text-align: center
}

#divcss1 {
	margin: 0 auto;
	width: 300px;
	height: 100px
}
</style>
<title>用户管理</title>
</head>

<body>
	<div class="topdist"></div>
	<div class="container">

		<div class="row" id="opreate_div">
			<div class="btn-group col-md-offset-1 pull-left "
				style="margin-bottom: 10px">
				<button type="button" class="btn ue-btn" onclick="addUser()">
					<span class="fa fa-plus"></span> 新增
				</button>
			</div>
		</div>

		<div id="divcss1" style="width: 90%;">
			<table id="userList" class="table table-bordered table-hover">
				<thead>
					<tr>
						<th data-field="num" width="10%">序号</th>
						<th data-field="zhanghao" width="20%">账号</th>
						<th data-field="xingming" width="10%">姓名</th>
						<th data-field="bumen" width="20%">部门</th>
						<th data-field="beizhu" width="20%">备注</th>
						<th data-field="cz" width="20%" data-render="renderCz">操作</th>
					</tr>
				</thead>
			</table>
		</div>

	</div>
</body>
</html>