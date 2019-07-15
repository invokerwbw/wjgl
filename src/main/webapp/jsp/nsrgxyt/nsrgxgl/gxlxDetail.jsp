<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>关系类型</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/jsp/nsrgxyt/nsrgxgl/gxlxDetail.js"></script>
</head>
<body>
	<!-- 操作标识 -->
	<input id="flag" name="flag" type="hidden" value="${flag}" />
	<!-- 关系分类代码 -->
	<input id="gxflDm" name="gxflDm" type="hidden" value="${gxlx.gxflDm }" />
	<!-- 关系类型编码 -->
	<input id="gxlxBm" name="gxlxBm" type="hidden" value="${gxlx.gxlxBm }" />

	<div class="container">
		<div style="float: right;">
			<button type="button" class="btn s-btn rtnBtn right">
				<span class="fa fa-rotate-left"></span>返回
			</button>
		</div>

		<div class="s-title s-border">
			<span><i class="fa fa-file-text-o"></i>基本信息</span>
		</div>
		<div class="s-xx_con">
			<form class="form-horizontal" role="form">
				<div class="jbxx">
					<label class="control-label"><span>关系类型编码：</span>${gxlx.gxlxBm }</label>
				</div>
				<div class="jbxx">
					<label class="control-label"><span>关系类型名称：</span>${gxlx.gxlxMc }</label>
				</div>
				<div class="jbxx">
					<label class="control-label"><span>关系类型来源表：</span>${gxlx.gxlxLyb }</label>
				</div>
				<div class="jbxx">
					<label class="control-label"><span>关系类型优先级：</span> <c:if
							test="${gxlx.gxlxYxj == '1'}">
						高
					</c:if> <c:if test="${gxlx.gxlxYxj == '2'}">
						中
					</c:if> <c:if test="${gxlx.gxlxYxj == '3'}">
						低
					</c:if></label>
				</div>
				<div class="jbxx">
					<label class="control-label"><span>关系类型发生周期：</span>${gxlx.gxlxFszqq }
						至 ${gxlx.gxlxFszqz }</label>
				</div>
			</form>
		</div>

		<div class="s-title s-border">
			<span><i class="fa fa-gear (alias)"></i>字段信息</span>
		</div>
		<table id="zdList">
			<thead>
				<tr>
					<th width="20%" data-field="zdmc">字段名称</th>
					<th width="40%" data-field="xsmc">显示名称</th>
					<th width="10%" data-field="zszyzd" data-render="renderSf">展示重要字段</th>
					<th width="10%" data-field="jszd" data-render="renderSf">检索字段</th>
					<th width="10%" data-field="gzpdzd" data-render="renderSf">规则判断字段</th>
				</tr>
			</thead>
		</table>

	</div>
</body>
</html>