<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>纳税人关系清单</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/jsp/nsrgxyt/nsrgxtj/gxLink.js"></script>
</head>
<body>
	<div class="container">
		<table id="gxLinkList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="15%" data-field="nsrsbh">纳税人识别号</th>
					<th width="25%" data-field="nsrmc">纳税人名称</th>
					<th width="10%" data-field="gxxlmc">关系名称</th>
					<th width="10%" data-field="gxcc">关系层次</th>
					<th width="15%" data-field="gxNsrsbh">关系纳税人识别号</th>
					<th width="25%" data-field="gxNsrmc">关系纳税人名称</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>