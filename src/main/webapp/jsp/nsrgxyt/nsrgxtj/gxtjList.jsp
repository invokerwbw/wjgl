<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>纳税人关系统计</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript"
	src="${ctx}/jsp/nsrgxyt/nsrgxtj/gxtjList.js"></script>
<style type="text/css">
.jbxxTable {
	font-family: microsoft yahei;
	height: 35px;
	line-height: 35px;
	text-align: left;
	width: 100%;
	max-width: 100%;
}

.s-ue-form {
	width: 50px;
	height: 30px;
	line-height: 30px;
	padding: 0 12px;
	border: 1px solid #ddd;
	border-radius: 0;
	-webkit-box-shadow: inset 0 0 0 rgba(0, 0, 0, 0);
	box-shadow: inset 0 0 0 rgba(0, 0, 0, 0);
	filter: progid:DXImageTransform.Microsoft.gradient(startcolorstr=#00000000,
		endcolorstr=#00000000)
}
</style>
</head>
<body>
	<div class="container">
		<form id="searchForm" class="form-inline" onsubmit="return false;">
			<button type="button" class="btn ue-btn searchBtn" id="searchBtn">
				<span class="fa fa-search"></span>查询
			</button>
			<button type="button" class="btn ue-btn resetBtn" id="resetBtn">
				<span class="fa fa-refresh"></span>重置
			</button>
			<button type="button" class="btn ue-btn">
				<span class="fa fa-cloud-download"></span>导出
			</button>
			<div class="jianju"></div>
			<div style="border: 1px solid #e7e7e7;">
				<table class="jbxxTable">
					<!-- <tr>
						<td width="5%"></td>
						<td width="12.5%"><span>纳税人识别号：</span></td>
						<td width="30%"><input type="text"
							class="form-control ue-form" id="nsrsbh" name="nsrsbh" /></td>
						<td width="5%"></td>
						<td width="12.5%"><span>关系纳税人识别号：</span></td>
						<td width="30%"><input type="text"
							class="form-control ue-form" id="gxNsrsbh" name="gxNsrsbh" /></td>
						<td width="5%"></td>
					</tr> -->
					<tr>
						<td width="5%"></td>
						<td width="12.5%">关系区域范围：</td>
						<td width="30%"><select class="form-control ue-form"
							id="gxqyfw" name="gxqyfw" style="width: 180px">
								<option value="">请选择</option>
						</select></td>
						<td width="5%"></td>
						<td width="12.5%">关系时间范围：</td>
						<td width="30%"><input type="text" class="s-ue-form"
							id="gxsjfwq" name="gxsjfwq" /><span>&nbsp;-&nbsp;</span><input
							type="text" class="s-ue-form" id="gxsjfwz" name="gxsjfwz" /><span>&nbsp;（月）&nbsp;</span></td>
						<td width="5%"></td>
					</tr>
					<tr>
						<td></td>
						<td>关系强度：</td>
						<td><select class="form-control ue-form" id="gxqd"
							name="gxqd" style="width: 180px">
								<option value="1">强</option>
								<option value="2">中</option>
								<option value="3">弱</option>
						</select></td>
						<td></td>
						<td>关系层次：</td>
						<td><input type="text" class="s-ue-form" id="gxsjfwq"
							name="gxccq" /><span>&nbsp;-&nbsp;</span><input type="text"
							class="s-ue-form" id="gxsjfwz" name="gxccz" /><span>&nbsp;（层）&nbsp;</span></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td>关系类型：</td>
						<td><select class="form-control ue-form" id="gxlx"
							name="gxlx" style="width: 180px">
								<option value="1">产权投资关系</option>
								<option value="2">关联交易关系</option>
								<option value="3">办税关联关系</option>
								<option value="4">涉水群体关系</option>
								<option value="5">其他关系</option>
						</select></td>
						<td></td>
						<td>关系名称：</td>
						<td><select class="form-control ue-form" id="gxxlDm"
							name="gxxlDm" style="width: 180px">
								<option value="">请选择</option>
								<option value="01001">直接投资关联关系</option>
								<option value="01002">间接投资关联关系</option>
								<option value="02001">直接投资关系</option>
								<option value="02002">间接投资关系</option>
								<option value="02011">总机构关系</option>
								<option value="02012">分支机构关系</option>
								<option value="00000">其他关系</option>
						</select></td>
						<td></td>
					</tr>
				</table>
			</div>
		</form>
		<table id="gxtjList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="15%" data-field="nsrsbh">纳税人识别号</th>
					<th width="19%" data-field="nsrmc">纳税人名称</th>
					<th width="8%" data-field="gxsl">关系数量</th>
					<th width="10%" data-field="gxnsrhs" data-render="renderLj">关系纳税人户数</th>
					<th width="8%" data-field="cqtzgx" data-render="renderLj">产权投资关系</th>
					<th width="8%" data-field="gljygx" data-render="renderLj">关联交易关系</th>
					<th width="8%" data-field="bsglgx" data-render="renderLj">办税关联关系</th>
					<th width="8%" data-field="ssqtgx" data-render="renderLj">涉税群体关系</th>
					<th width="8%" data-field="qtglgx" data-render="renderLj">其他关联关系</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>