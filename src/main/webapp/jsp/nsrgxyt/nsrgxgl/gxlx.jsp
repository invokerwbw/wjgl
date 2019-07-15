<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>关系类型</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/jsp/nsrgxyt/nsrgxgl/gxlx.js"></script>
</head>
<body>
	<!-- 操作标识 -->
	<input id="flag" name="flag" type="hidden" value="${flag}" />
	<!-- Content-Type值 -->
	<input id="gxlxYxjVal" name="gxlxYxjVal" type="hidden"
		value="${gxlx.gxlxYxj}" />

	<div class="container">
		<div style="float: right;">
			<button type="button" class="btn s-btn rtnBtn right">
				<span class="fa fa-rotate-left"></span>返回
			</button>
		</div>
		<form class="form-horizontal" id="gxlxForm" name="gxlxForm"
			onsubmit="return false;">
			<!-- 关系分类代码 -->
			<input id="gxflDm" name="gxflDm" type="hidden"
				value="${gxlx.gxflDm }" />

			<div class="s-title s-border" style="margin-top: 0px;">
				<span><i class="fa fa-file-text-o"></i>基本信息</span><b>请填写关系类型名称、关系类型来源表等基本信息</b>
			</div>
			<div class="s-xx_con">
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型编码<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<input type="text" class="form-control ue-form s-Validform_input"
							id="gxlxBm" name="gxlxBm" value="${gxlx.gxlxBm }"
							placeholder="关系类型编码" readonly="readonly" /> <span
							class="Validform_checktip Validform_span s-Validform_span">
						</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型名称<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<input type="text" class="form-control ue-form s-Validform_input"
							id="gxlxMc" name="gxlxMc" value="${gxlx.gxlxMc }"
							placeholder="关系类型名称" datatype="*1-100" errormsg="请输入1至100个字符"
							nullmsg="请输入关系类型名称" /> <span
							class="Validform_checktip Validform_span s-Validform_span">&nbsp;1至100个字符</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型来源表<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<input type="text" class="form-control ue-form s-Validform_input"
							id="gxlxLyb" name="gxlxLyb" value="${gxlx.gxlxLyb }"
							placeholder="关系类型来源表" datatype="/^[0-9a-zA-Z_]{1,20}$/"
							errormsg="请输入正确格式的关系类型来源表，如：DJ_NSRXX" nullmsg="请输入关系类型来源表" /> <span
							class="Validform_checktip Validform_span s-Validform_span">&nbsp;如：DJ_NSRXX</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型优先级<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<select class="form-control ue-form s-Validform_input"
							id="gxlxYxj" name="gxlxYxj" datatype="s" nullmsg="请选择关系类型优先级">
							<option value="">请选择</option>
							<option value="1">高</option>
							<option value="2">中</option>
							<option value="3">低</option>
						</select> <span class="Validform_checktip Validform_span s-Validform_span"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型发生周期起<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<div class="input-group date col-xs-5 col-md-5" id="gxlxFszqqDiv">
							<div id="gxlxFszqstart">
								<input type="text"
									class="form-control ue-form ss-Validform_input"
									name="gxlxFszqq" id="gxlxFszqq" value="${gxlx.gxlxFszqq }"
									readonly="readonly" />
							</div>
							<span class="input-group-addon ue-form-btn"><i
								class="fa fa-calendar"></i></span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">关系类型发生周期止<span
							class="required">*</span></label>
					</div>
					<div class="col-xs-10 col-md-10">
						<div class="input-group date col-xs-5 col-md-5" id="gxlxFszqzDiv">
							<div id="gxlxFszqend">
								<input type="text"
									class="form-control ue-form ss-Validform_input"
									name="gxlxFszqz" id="gxlxFszqz" value="${gxlx.gxlxFszqz }"
									readonly="readonly" />
							</div>
							<span class="input-group-addon ue-form-btn"><i
								class="fa fa-calendar"></i></span>
						</div>
					</div>
				</div>
			</div>

			<div class="s-title s-border">
				<span><i class="fa fa-gears (alias)"></i>字段信息</span><b>请填写字段信息</b>
			</div>
			<div class="s-xx_con">
				<div class="form-group">
					<div class="col-xs-2 col-md-2 s-control-label">
						<label class="s-control-label">字段列表<span class="required">*</span></label>
					</div>
					<div class="col-xs-9 col-md-9" style="margin-top: 6px;">
						<button type="button" class="btn btn-xs ue-btn"
							onclick="zdAddRow()">
							<span class="fa fa-plus"></span>增加
						</button>
						<button type="button" class="btn btn-xs ue-btn"
							onclick="zdDelRow()">
							<span class="fa fa-minus"></span>删除
						</button>
						<div class="clear"></div>
						<div class="jianju"></div>
						<table id="zdList">
							<thead>
								<tr>
									<th width="10%" data-field="id" data-render="checkbox"></th>
									<th width="20%" data-field="zdmc" data-editor="text"
										data-validator="isName">字段名称</th>
									<th width="40%" data-field="xsmc" data-editor="text"
										data-validator="isLegal">显示名称</th>
									<th width="10%" data-field="zszyzd" data-editor="select"
										data-source="getSfSelectVal" data-render="renderSf">展示重要字段</th>
									<th width="10%" data-field="jszd" data-editor="select"
										data-source="getSfSelectVal" data-render="renderSf">检索字段</th>
									<th width="10%" data-field="gzpdzd" data-editor="select"
										data-source="getSfSelectVal" data-render="renderSf">规则判断字段</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>

			<div class="jianju"></div>
			<div class="jianju"></div>
			<div class="row">
				<div class="col-xs-12 col-md-12" style="text-align: center;">
					<button type="button" class="btn ue-btn-primary" id="save">保存</button>
				</div>
			</div>
			<div class="jianju"></div>
			<div class="jianju"></div>

		</form>
	</div>
</body>
</html>