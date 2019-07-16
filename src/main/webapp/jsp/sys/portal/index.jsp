<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准检索系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/jsp/sys/portal/index_new.css" />
<script type="text/javascript" src="${ctx}/jsp/sys/portal/index.js"></script>
<%  
    String user = (String)request.getSession().getAttribute("en_name");  
%>
</head>
<body>
	<!-- 页面结构 -->
	<input id="userId" type="hidden" value="<%=user%>" />
	<header class="navbar navbar-static-top">
		<div class="navbar-header">
			<a class="fa fa-bars pull-left" onclick="toggleSide()"></a> <a
				href="javascript:void(0)" class="navbar-brand"><span></span></a>
		</div>
		<nav class="collapse navbar-collapse">
			<ul class="nav navbar-nav navbar-right">
				<li>
					<!-- <div class="dropdown">
						<a class="fa fa-star-o" data-toggle="dropdown"></a>
						<ul
							class="dropdown-menu ue-dropdown-menu dropdown-menu-right drop-area">
							<li class="ue-dropdown-angle"></li>
							<li><a href="javascript:;">行政机构管理</a></li>
							<li><a href="javascript:;">业务机构管理</a></li>
							<li><a href="javascript:;">组织机构视角管理</a></li>
							<li><a href="javascript:;">用户管理</a></li>
							<li><a href="javascript:;">角色管理</a></li>
							<li><a href="javascript:;">员工入职管理</a></li>
							<li><a href="javascript:;" class="more">更多</a></li>
						</ul>
					</div> -->
				</li>
				<li>
					<%-- <div class="dropdown">
						<a class="fa fa-th-large" data-toggle="dropdown"></a>
						<div
							class="dropdown-menu ue-dropdown-menu dropdown-menu-right app">
							<span class="ue-dropdown-angle"></span>
							<div class="drop-area app">
								<div class="ue-icon-title" id="bsp">
									<img src="${ctx}/jsp/sys/portal/images/bsp.png" class="ue-icon" />
									<a class="ue-title ellipsis" title="BSP">BSP</a>
								</div>
								<div class="ue-icon-title" id="bpm">
									<img src="${ctx}/jsp/sys/portal/images/bpm.png" class="ue-icon" />
									<a class="ue-title ellipsis" title="BPM">BPM</a>
								</div>
								<div class="ue-icon-title" id="cform">
									<img src="${ctx}/jsp/sys/portal/images/cform.png"
										class="ue-icon" /> <a class="ue-title ellipsis" title="CFORM">CFORM</a>
								</div>
								<div class="ue-icon-title" id="bsp">
									<img src="${ctx}/jsp/sys/portal/images/other.png"
										class="ue-icon" /> <a class="ue-title ellipsis" title="其他构件">其他构件</a>
								</div>
								<div class="ue-icon-title" id="bpm">
									<img src="${ctx}/jsp/sys/portal/images/personal.png"
										class="ue-icon" /> <a class="ue-title ellipsis" title="个人设置">个人设置</a>
								</div>
								<div class="ue-icon-title" id="cform">
									<img src="${ctx}/jsp/sys/portal/images/safety.png"
										class="ue-icon" /> <a class="ue-title ellipsis" title="安全管理">安全管理</a>
								</div>
								<div class="ue-icon-title" id="bpm">
									<img src="${ctx}/jsp/sys/portal/images/organization.png"
										class="ue-icon" /> <a class="ue-title ellipsis"
										title="组织管理测试文本">组织管理测试文本</a>
								</div>
								<div class="ue-icon-title" id="cform">
									<img src="${ctx}/jsp/sys/portal/images/case.png"
										class="ue-icon" /> <a class="ue-title ellipsis" title="案例">案例</a>
								</div>
							</div>
							<a href="javascript:;" class="more">更多</a>
						</div>
					</div> --%>
				</li>
				<li>
					<div class="dropdown">
						<a data-toggle="dropdown" style="color: #fff;">${sessionScope.en_name}<span
							class="caret"></span></a>
						<div class="dropdown-menu ue-dropdown-menu dropdown-menu-right">
							<span class="ue-dropdown-angle"></span> <img class="user-photo"
								src="${ctx}/jsp/sys/portal/images/user.jpg" />
							<div class="user-info">
								<span class="user-role">${sessionScope.en_name}</span> <a
									href="javascript:;" class="user-action"><i
									class="fa fa-edit">&nbsp;</i>修改资料</a> <a href="javascript:;"
									class="user-action"><i class="fa fa-user-md">&nbsp;</i>个人中心</a>
							</div>
							<div class="exit">
								<a onclick="logout()">退出</a>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</nav>
	</header>

	<div class="ue-menu-wrap">
		<div class="ue-menu-left">
			<div class="ue-left-top ue-pie">
				<span id="dyn-top"><img class="title-icon"
					src="${ctx}/jsp/sys/portal/images/organization.png" />菜单</span>
			</div>
			<div class="ue-left-content">
				<div class="ue-vmenu">
					<ul>
						<li><a
							onclick="loadUrl('标准检索', '${ctx}/service/search/toStdList')"
							data-role="leaf">标准检索</a></li>
						<li style="display: none" id="update"><a
							onclick="loadUrl('标准更新', '${ctx}/service/update/bzgx')"
							data-role="leaf">标准更新</a></li>
						<li style="display: none" id="modify"><a
							onclick="loadUrl('标准维护', '${ctx}/service/modify/list')"
							data-role="leaf">标准维护</a></li>
						<li style="display: none"><a
							onclick="loadUrl('用户管理', '${ctx}/service/sys/userListPage')"
							data-role="leaf">用户管理</a></li>

					</ul>
				</div>
			</div>
		</div>
		<div class="ue-menu-right">
			<div class="ue-right-top ue-pie">Welcome</div>
			<div class="ue-right-content">
				<iframe id="mainFrame" name="mainFrame"
					src="${ctx}/service/search/toStdList" frameborder="0"
					allowtransparency="true" width="100%" height="100%"></iframe>
			</div>
		</div>
	</div>

</body>
</html>