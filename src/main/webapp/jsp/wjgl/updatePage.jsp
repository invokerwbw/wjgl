<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>标准更新</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<link href="../../skins/skin/css/wjgl/web.css" rel="stylesheet"
	type="text/css">
<!--更新操作-->
<script>
	function updateOrg() {

		$.dialog({
			type : 'iframe',
			url : ctx + '/service/update/opreatePage',
			title : '标准组织更新',
			width : 500,
			height : 350,
			onshow : function() {
			},
			onclose : function() {
			},
			oniframeload : function() {
			}
		})
		/*
		$.dialog({
			autofocus:true,
			type: 'confirm',
			content: "请确定移除条信息？",
			cancel: function(){},
			ok: function(){
				this.close();
				$.ajax({
					type: "get",
					traditional :true,
					url: ctx + "/service/update/updateOrg",
					data:{
					},
					error:function(data){
						$.sticky("error", {
							style : 'error',
							autoclose : 500,
							position : 'center'
						});
					},
					success:function(data){
						if(data.flag == "success"){
							$.sticky("更新成功！", {
								style : 'success',
								autoclose : 500,
								position : 'center'
							});
						}else{
							sticky("移除失败");
						}
					}  
				});
				return false;
			}
		});
		 */
	}
</script>
</head>
<body>

	<div class="public">
		<div class="sevice">
			<div class="s_box">
				<div class="pub_t">
					<h2>
						更新类别<span>/Content</span>
					</h2>
					<div class="line"></div>
				</div>
				<ul class="content">
					<li><button onclick="updateOrg()">标准组织</button></li>
					<li><a href="${ctx}/jsp/wjgl/updateBib.jsp">标准题录</a></li>
					<li><a href="${ctx}/jsp/wjgl/updateTxt.jsp">标准文本</a></li>
				</ul>
			</div>

			<!-- <div class="s_box">
			<div class="pub_t">
				<h2>更新历史<span>/History</span></h2>
				<div class="line"></div>
			</div>
			<div class="tmbl">
				<h3>标准更新记录<a href="#">+MORE</a></h3>
              <table id="stdList" class="table table-bordered table-hover">
				<thead>
				<tr>
					<th width="15%" data-field="bzh">序号</th>
					<th width="40%" data-field="bzmc" data-render="renderBzmc">类别</th>
					<th width="10%" data-field="fbrq">更新时间</th>
					<th width="10%" data-field="ssrq">更新组织</th>
					<th width="5%" data-field="ys">更新题录</th>
					<th width="10%" data-field="zt">更新文本</th>
					<th width="10%" data-field="cz" data-render="renderCz">操作者</th>
				</tr>
				</thead>
			</table>
			</div>

		</div> -->

		</div>
	</div>
</body>
</html>
