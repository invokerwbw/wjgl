<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<title>标准检索系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=8"> <!--以IE8模式渲染-->
<%@ include file="/jsp/public/include/taglib.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/jsp/sys/welcome/index.css" />
</head>
<body>
	<!-- 页面结构 -->
	<div class="login">
		<header class="navbar navbar-static-top login-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand"><span>|</span>&nbsp&nbsp标准检索系统</a>
				</div>
				<nav class="navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a>&nbsp</a></li>
					</ul>
				</nav>
			</div>
		</header>
		<div class="login-body pull-right">
			<div class="avatar">
				<h4>欢迎</h4>
				<img src="${ctx}/jsp/sys/welcome/images/user-avatar.png" />
			</div>
			<ul id="loginTab" class="nav nav-tabs ue-tabs">
				<li class="active"><a href="#yg" data-toggle="tab">员工</a></li>
				<li><a href="#gly" data-toggle="tab">管理员</a></li>
			</ul>
			<div style="height: 10px;"></div>
			<div id="loginTabContent" class="tab-content">
				<div class="tab-pane active" id="yg">
					<div style="text-align: center;">
						<button class="btn" onclick="ygLogin();" style="display: inline;background-color: #3e99ff;color: #fff;">直接登录</button>
					</div>
				</div>
				<div class="tab-pane" id="gly">
					<form method="post" onsubmit="return false;">
						<div class="form-group">
							<input type="text" class="form-control ue-form" id="username"
								name="j_username" placeholder="请输入用户名" value=""><a
								id="deleteName" class="quickdelete" href="javascript:void(0)"
								onclick="clearName()" title="清空"></a>
						</div>
						<div class="form-group">
							<input type="password" class="form-control ue-form" id="password"
								name="j_password" placeholder="请输入密码" value=""><a
								id="deletePassword" class="quickdelete"
								href="javascript:void(0)" onclick="clearPassword()" title="清空"></a>
						</div>
						<button class="btn btn-login" onclick="login();">登录</button>
						<button class="btn btn-other" onclick="reset();">重置</button>
					</form>
				</div>
			</div>
		</div>
		<div class="login-bottom">
			<div class="bottom-content">
				<a class="no-border"></a><a class="no-border">齐鲁工业大学（山东省科学院）</a><a
					class="no-border">版权所有</a>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	function ygLogin() {
		    document.forms[0].j_username.value="guest";
		    document.forms[0].j_password.value="123456";
		    login();
	}
	
	function login() {
		if(!check()) return;
		
			document.forms[0].action = "<%=request.getContextPath()%>/service/sysLogin/login";
			document.forms[0].submit();

		}
		function check() {
			var username = document.forms[0].j_username;
			var password = document.forms[0].j_password;
			if (username.value == "" && password.value == "") {
				$.sticky('请输入用户名和密码', {
					style : 'warning',
					autoclose : 5000,
					position : 'center'
				});
				username.focus();
				return false;
			}
			if (username.value == "") {
				$.sticky('请输入用户名', {
					style : 'warning',
					autoclose : 5000,
					position : 'center'
				});
				username.focus();
				return false;
			}
			if (password.value == "") {
				$.sticky('请输入密码', {
					style : 'warning',
					autoclose : 5000,
					position : 'center'
				});
				password.focus();
				return false;
			}
			return true;

		}
		//'enter' key,/=47,*=42,+=43
		function keypress(e) {

			if (!window.event) {
				e = e.which;
			} else {
				e = window.event.keyCode;
			}
			if (e == 13 || e == 42) //'enter' key,*
			{
				doLogin();
			}

		}
		//用户姓名输入框清空按钮
		$("#username").bind("input propertychange", function() {
			if ($("#username")[0].value != "")
				$("#deleteName").show();
			else
				$("#deleteName").hide();
		});
		function clearName() {
			$("#userName")[0].value = "";
			$("#deleteName").hide();
		}
		//用户密码输入框清空按钮
		$("#password").bind("input propertychange", function() {
			if ($("#password")[0].value != "")
				$("#deletePassword").show();
			else
				$("#deletePassword").hide();
		});
		function clearPassword() {
			$("#password")[0].value = "";
			$("#deletePassword").hide();
		}
	</script>
	<script> 
		  var errori ='<%=request.getAttribute("errorMessage")%>';
		if (errori == 'userError') {
			$.sticky('当前登录账户不存在', {
				style : 'error',
				autoclose : 5000,
				position : 'center'
			});
			username.focus();
		} else if (errori == 'pwdError') {
			$.sticky('密码错误', {
				style : 'error',
				autoclose : 5000,
				position : 'center'
			});
			password.focus();
		}
	</script>
</body>
</html>