<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>关系分类</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/jsp/nsrgxyt/nsrgxgl/gxflList.js"></script>
</head>
<body>
	<div class="container">
		<button type="button" class="btn ue-btn addBtn">
			<span class="fa fa-plus"></span>添加关系分类
		</button>
		<table id="gxflList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="20%" data-field="gxflDm" data-sortable="false">关系分类代码</th>
					<th width="30%" data-field="gxflMc" data-sortable="false">关系分类名称</th>
					<th width="10%" data-field="yxbz" data-sortable="false"
						data-render="renderYxbz">有效标志</th>
					<th width="40%" data-field="yxbz" data-sortable="false"
						data-render="renderCzBtn">操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>