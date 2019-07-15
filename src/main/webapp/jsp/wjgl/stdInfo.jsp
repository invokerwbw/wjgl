<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>基本信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<link href="../../skins/skin/css/wjgl/base.css" rel="stylesheet"
	type="text/css">
<link href="../../skins/skin/css/wjgl/view.css" rel="stylesheet"
	type="text/css">
<link href="../../skins/skin/css/wjgl/webox.css" rel="stylesheet"
	type="text/css">
<link href="../../skins/skin/css/wjgl/header.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript">
	function readBtn() {
		var bzh = document.getElementById("bzh").innerText;

		if (bzh != undefined && bzh != null && bzh != '') {
			$.ajax({
				url : ctx + "/service/fileOperate/checkPDFIsExist?bzh=" + bzh,
				dataType : 'json',
				success : function(data) {
					if (data.flag == false) {
						$.dialog({
							type : 'confirm',
							content : data.message,
							ok : function() {
							},
							cancel : false
						});
					} else {
						window.location.href = ctx
								+ "/service/fileOperate/readPdf?bzh=" + bzh;
					}
				}
			});
		}
	};
	function downloadBtn() {
		var bzh = document.getElementById("bzh").innerText;

		if (bzh != undefined && bzh != null && bzh != '') {
			$.ajax({
				url : ctx + "/service/fileOperate/checkPDFIsExist?bzh=" + bzh,
				dataType : 'json',
				success : function(data) {
					if (data.flag == false) {
						$.dialog({
							type : 'confirm',
							content : data.message,
							ok : function() {
							},
							cancel : false
						});
					} else {
						window.location.href = ctx
								+ "/service/fileOperate/download?bzh=" + bzh;
					}
				}
			});
		}
	};

	function checkBiBExist(bzh) {

		if (bzh != undefined && bzh != null && bzh != '') {
			$.ajax({
				url : ctx + "/service/fileOperate/checkBzhIsExist?bzh=" + bzh,
				dataType : 'json',
				success : function(data) {
					if (data.flag == false) {
						$.dialog({
							type : 'confirm',
							content : data.message,
							ok : function() {
							},
							cancel : false
						});
					} else {
						window.open(ctx + "/service/fileOperate/stdInfo?bzh="
								+ bzh);
					}
				}
			});
		}
	}
</script>
</head>

<body class="input list">


	<div class="header"></div>

	<div class="content">
		<div class="left">

			<div class="jiben">
				<div class="title">基本信息</div>
				<table border="0" cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
							<td height="40" colspan="6" valign="middle" id="bzhss"><b
								id="bzh">${map.bzh}</b> <span>${map.zt}</span></td>
						</tr>
						<tr>
							<td width="96" height="40" valign="top"><p>中文名称：</p></td>
							<td height="40" colspan="5" valign="top">${map.zwmc}</td>
						</tr>
						<tr>
							<td height="40" valign="top"><p>英文名称：</p></td>
							<td height="40" colspan="5" valign="top">${map.ywmc }</td>
						</tr>
						<tr>
							<td height="40" valign="top"><p>范&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;围：</p></td>
							<td height="40" colspan="5" valign="top">${map.fw}</td>
						</tr>
						<tr class="x">
							<td height="40" valign="middle"><p>发布日期：</p></td>
							<td valign="middle">${map.fbrq }</td>
							<td height="40" align="right" valign="middle"><p>实施日期：</p></td>
							<td valign="middle">${map.ssrq }</td>
							<td align="right" valign="middle"><p>废止日期：</p>${map.fzrq}</td>
							<td height="40" valign="middle"></td>
						</tr>
						<tr class="x">
							<td height="40" valign="middle"><p>标准组织：</p></td>
							<td valign="middle">${map.bzzz}</td>
							<td height="40" align="right" valign="middle"><p>全文页数：</p></td>
							<td valign="middle">${map.qwys}页</td>
							<td align="right" valign="middle"><p>正文语种：</p>${map.zwyz}</td>
							<td height="40" valign="middle"></td>
						</tr>
					</tbody>
				</table>
				<div class="pdf">
					<img title="预览" onclick="readBtn()"
						src="../../skins/skin/img/read.png" style="cursor: pointer" />
					&nbsp;&nbsp; <img title="下载" onclick="downloadBtn()"
						src="../../skins/skin/img/download.png" style="cursor: pointer" />
				</div>
			</div>

			<div class="guan">
				<div class="title">关联标准</div>
				<div class="g_con">
					<div>
						<p>代替标准</p>
						<h6 style="font-weight: bold;">
							<c:forEach items="${map.dtbzList}" var="dtbzMap">
								<a style="display: block; margin-top: 3px;"
									onclick="return checkBiBExist('${dtbzMap.a100}');">
									${dtbzMap.a100}&nbsp;${dtbzMap.a298}</a>
								<br />
							</c:forEach>
						</h6>
					</div>
					<div>
						<p>被代替标准</p>
						<h6 style="font-weight: bold;">
							<c:forEach items="${map.bdtbzList}" var="bdtbzMap">
								<a style="display: block; margin-top: 3px;"
									onclick="return checkBiBExist('${bdtbzMap.a100}');">
									${bdtbzMap.a100}&nbsp;${bdtbzMap.a298} </a>
								<br />
							</c:forEach>
						</h6>
					</div>
					<div>
						<p>采用标准</p>
						${map.cybz}
					</div>
				</div>
			</div>

			<div class="xiu">
				<div class="title">修改单</div>
				<div class="x_con">${map.xgd}</div>
			</div>

			<div class="qicao">
				<div class="title">标准起草单位</div>
				<div class="q_con">${map.qcdw}</div>
			</div>

			<div class="fenlei">
				<div class="title">标准分类号</div>
				<div class="f_con">
					<table width="140" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td width="70" height="30" align="right"><p>ICS分类号：</p></td>
								<td height="30">${map.icsflh}</td>
							</tr>
							<tr>
								<td width="70" height="30" align="right"><p>中国标准分类号：</p></td>
								<td height="30">${map.zbflh}</td>
							</tr>
							<tr>
								<td width="70" height="30" align="right"><p>经济行业分类号：</p></td>
								<td height="30">${map.gmhyflh}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</div>

		<!--右边内容-->
		<div class="right"></div>
	</div>
</body>
</html>