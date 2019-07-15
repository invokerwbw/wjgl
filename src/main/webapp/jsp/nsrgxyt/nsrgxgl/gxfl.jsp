<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>关系分类</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/jsp/nsrgxyt/nsrgxgl/gxfl.js"></script>
</head>
<body>
	<div class="container" style="border: 1px solid #EFEEEE;">
		<input id="flag" name="flag" type="hidden" value="${flag}" />
		<form class="form-horizontal" id="gxflForm" name="gxflForm"
			onsubmit="return false">
			<div class="form-group">
				<label class="col-sm-3 control-label">关系分类代码</label>
				<div class="col-sm-8">
					<input type="text" class="form-control ue-form Validform_input"
						id="gxflDm" name="gxflDm" value="${gxfl.gxflDm }"
						placeholder="关系分类代码" readonly="readonly" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">关系分类名称<span
					class="required">*</span></label>
				<div class="col-sm-8">
					<input type="text" class="form-control ue-form Validform_input"
						id="gxflMc" name="gxflMc" value="${gxfl.gxflMc }"
						placeholder="关系分类名称" datatype="*1-100" errormsg="请输入1至100个字符"
						nullmsg="请输入关系分类名称" /> <span
						class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-8">
					<button type="button" class="btn ue-btn-primary" id="save">
						保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>