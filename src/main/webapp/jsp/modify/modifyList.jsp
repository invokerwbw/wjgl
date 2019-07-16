<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准维护</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<link href="${ctx}/skins/skin/css/wjgl/paper.css" rel="stylesheet" type="text/css">
<link href="${ctx}/skins/skin/css/wjgl/search.css" rel="stylesheet"	type="text/css">
<script type="text/javascript" src="${ctx}/jsp/modify/modifyList.js"></script>
</head>
<body>
	<div class="container">
		
		<form id="searchForm" class="form-inline" onsubmit="return false;">
			<table>
				<tr>
					<td colspan="12"><input id="keyword" name="keyword" type="text"
						class="form-control ue-form" value="" placeholder="请输入标准号或标准名称" />
					</td>
					<td><span>&nbsp;&nbsp;标准组织&nbsp;&nbsp;</span></td>
					<td>
						<select class="form-control ue-form" id="bzzz" name="bzzz" style="width: 180px">
							<option value="">--全部--</option>
							<option value="CB">CB 船舶</option>
							<option value="DL">DL 电力</option>
							<option value="GB">GB 国标</option>
							<option value="GJB">GJB 国军标</option>
							<option value="HG">HG 化工</option>
							<option value="HB">HB 航空</option>
							<option value="JB">JB 机械</option>
							<option value="SH">SH 石化</option>
							<option value="YB">YB 黑色冶金</option>
							<option value="YS">YS 有色金属</option>
						</select>
					</td>
					<td colspan="2">
						<span>&nbsp;&nbsp;标准状态&nbsp;&nbsp;</span>
						<input type="checkbox" class="check" id="xianxing" name="a200s"	value="现行"><label for="xianxing">现行 </label>
                        <input type="checkbox" class="check" id="weishengxiao" name="a200s"	value="未生效"><label for="weishengxiao">未生效 </label>
                        <input type="checkbox" class="check" id="feizhi" name="a200s" value="废止"><label for="feizhi">废止</label>
					</td>
				</tr>
				<tr>
					<td colspan="7"><div class="jianju"></div></td>
				</tr>
				<tr>
					<td colspan="5">
					    <button type="button" class="btn ue-btn searchBtn" id="searchBtn">
							<span class="fa fa-search"></span>查询
						</button>
						<button type="button" class="btn ue-btn resetBtn" id="resetBtn">
							<span class="fa fa-refresh"></span>重置
						</button>
						<button type="button" class="btn ue-btn addBtn" id="addBtn">
							<span class="fa fa-plus"></span>新增
						</button>
					</td>
				</tr>
			</table>
		</form>
		
		<div>
			<br/>
		</div>
		
		<div>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "all" onclick="groupList('all')"></a>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "gb" onclick="groupList('gb')"></a>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "hb" onclick="groupList('hb')"></a>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "db" onclick="groupList('db')"></a>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "tb" onclick="groupList('tb')"></a>
			<a href="#javascript:void(0);" style="font-weight: bold; margin-left: 5px" id = "gw" onclick="groupList('gw')"></a>
			&nbsp;&nbsp;&nbsp;<span style="color:red"><B>&larr;点击按标准主体分类查看</B></span>
		</div>
		
		<table id="dataList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="15%" data-field="bzh">标准号</th>
					<th width="45%" data-field="bzmc" data-render="renderBzmc">标准名称</th>
					<th width="10%" data-field="fbrq">发布日期</th>
					<th width="10%" data-field="ssrq">实施日期</th>
					<th width="5%" data-field="ys">页数</th>
					<th width="5%" data-field="zt">状态</th>
					<th width="10%" data-field="cz" data-render="renderCz">操作</th>
				</tr>
			</thead>
		</table>
		
	</div>
</body>
</html>