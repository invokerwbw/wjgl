<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准维护</title>
<%@ include file="/jsp/public/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/jsp/modify/std.js"></script>
</head>
<body>
	<div class="container" style="border: 1px solid #EFEEEE;">
		<input id="flag" name="flag" type="hidden" value="${flag}" />
		<input id="ztValue" name="ztValue" type="hidden" value="${std.zt}" />
		<form class="form-horizontal" id="stdForm" name="stdForm" onsubmit="return false">
			<div class="form-group">
				<label class="col-sm-3 control-label">标&nbsp;&nbsp;准&nbsp;&nbsp;号<span class="required">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="bzh" name="bzh" value="${std.bzh}"
						placeholder="标准号" datatype="*" nullmsg="请输入标准号" />
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">标准状态<span class="required">*</span></label>
				<div class="col-sm-9">
					<select class="form-control ue-form Validform_input" id="zt" name="zt" datatype="s" nullmsg="请选择标准状态">
						<option value="">请选择</option>
						<option value="现行">现行</option>
						<option value="未生效">未生效</option>
						<option value="废止">废止</option>
					</select>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">中文名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="zwmc" name="zwmc" value="${std.zwmc}" placeholder="中文名称"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">英文名称</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="ywmc" name="ywmc" value="${std.ywmc}" placeholder="英文名称"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">范&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;围</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="fw" name="fw" value="${std.fw}" placeholder="范围"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">发布日期</label>
				<div class="col-sm-9">
					<div class="input-group date Validform_input" >
						<div id="fbrq"><input type="text" class="form-control ue-form"
							id="fbrqVal" name="fbrqVal" value="${std.fbrq}" placeholder="发布日期"/></div>
                        <span class="input-group-addon ue-form-btn"><i class="fa fa-calendar"></i></span>								   
					</div>
					<span class="Validform_checktip Validform_span" ></span>		
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">实施日期</label>
				<div class="col-sm-9">
					<div class="input-group date Validform_input" >
						<div id="ssrq"><input type="text" class="form-control ue-form"
							id="ssrqVal" name="ssrqVal" value="${std.ssrq}" placeholder="实施日期"/></div>
                        <span class="input-group-addon ue-form-btn"><i class="fa fa-calendar"></i></span>								   
					</div>
					<span class="Validform_checktip Validform_span" ></span>		
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">废止日期</label>
				<div class="col-sm-9">
					<div class="input-group date Validform_input" >
						<div id="fzrq"><input type="text" class="form-control ue-form"
							id="fzrqVal" name="fzrqVal" value="${std.fzrq}" placeholder="废止日期"/></div>
                        <span class="input-group-addon ue-form-btn"><i class="fa fa-calendar"></i></span>								   
					</div>
					<span class="Validform_checktip Validform_span" ></span>		
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">组&nbsp;&nbsp;织&nbsp;&nbsp;号<span class="required">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="zzh" name="zzh" value="${std.zzh}" placeholder="组织号" datatype="*" nullmsg="请输入组织号"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">全文页数<span class="required">*</span></label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="qwys" name="qwys" value="${std.qwys}"
						placeholder="全文页数" datatype="n" nullmsg="请输入全文页数" errormsg="请输入数字！"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">正文语种</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="zwyz" name="zwyz" value="${std.zwyz}" placeholder="正文语种"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">代替标准</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="dtbz" name="dtbz" value="${std.dtbz}" placeholder="多个起代替标准英文分号分隔"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">被代替标准</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="bdtbz" name="bdtbz" value="${std.bdtbz}" placeholder="多个被代替标准使用英文分号分隔"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">采用标准</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="cybz" name="cybz" value="${std.cybz}" placeholder="采用标准"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">修&nbsp;&nbsp;改&nbsp;&nbsp;单</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="xgd" name="xgd" value="${std.xgd}" placeholder="修改单"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">起草单位</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="qcdw" name="qcdw" value="${std.qcdw}" placeholder="多个起草单位使用英文分号分隔"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">ICS号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="icsflh" name="icsflh" value="${std.icsflh}" placeholder="ICS分类号"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">中标分类号</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="zbflh" name="zbflh" value="${std.zbflh}" placeholder="中国标准分类号"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">国民经济分类</label>
				<div class="col-sm-9">
					<input type="text" class="form-control ue-form Validform_input"
						id="gmhyflh" name="gmhyflh" value="${std.gmhyflh}" placeholder="经济行业分类号"/>
					<span class="Validform_checktip Validform_span"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-9">
					<button type="button" class="btn ue-btn-primary" id="save">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>