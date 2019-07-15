<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>关系类型</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/jsp/nsrgxyt/nsrgxgl/gxlxList.js"></script>
</head>
<body>
	<div class="container">
		<button type="button" class="btn ue-btn addBtn">
			<span class="fa fa-plus"></span>添加关系类型
		</button>
		<button type="button" class="btn ue-btn rtnBtn">
			<span class="fa fa-rotate-left"></span>返回
		</button>
		<input id="gxflDm" name="gxflDm" type="hidden" value="${gxflDm}" />
		<table id="gxlxList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="20%" data-field="gxlxBm" data-sortable="false">关系类型编码</th>
					<th width="30%" data-field="gxlxMc" data-sortable="false">关系类型名称</th>
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