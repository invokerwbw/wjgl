$(document).ready(function() {
	var options = {};
	var url = ctx + "/service/search/listUser";
	grid = new L.FlexGrid("userList", url);
	grid.init(options);
});

function renderCz(data, type, full) {
	var account = data;
	data = '<button class=\"btn btn-info\" type=\"button\" onclick=\"editUser(\''
			+ account + '\')\">编辑</button>';
	data = data
			+ '&nbsp; &nbsp;'
			+ '<button class=\"btn btn-danger\" type=\"button\" onclick=\"deleteUser(\''
			+ account + '\')\">删除</button>';
	return data;
}

function editUser(data) {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/sys/userInfoPage?account=' + data,
		title : '修改用户',
		width : 450,
		height : 360,
		onshow : function() {
		},
		onclose : function() {
		}
	});
}

function deleteUser(data) {
	$.ajax({
		type : "get",
		url : ctx + "/service/sys/deleteUser?account=" + data,
		error : function(data) {
			$.sticky("服务器错误！", {
				style : 'error',
				autoclose : 1000,
				position : 'center'
			});
		},
		success : function(data) {
			var deleteFlag = data.deleteFlag;
			if (deleteFlag == "success") {
				$.sticky("删除成功!", {
					style : 'success',
					autoclose : '1000',
					position : 'center'
				});
				window.setTimeout(function() {
					document.location.reload();
				}, 1000);

			} else if (deleteFlag == "error") {
				$.sticky("删除失败!", {
					style : 'error',
					autoclose : 1000,
					position : 'center'
				});
			}
		}
	})
}

function addUser() {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/sys/userInfoPage',
		title : '添加用户',
		width : 450,
		height : 360,
		onshow : function() {

		},
		onclose : function() {
		}
	});
}

function userSave() {

	var editFlag = $("#userAccount").val();// 编辑用户，flag!=null; 新增用户，flag=null

	var account = $("#account").val();
	var password = $("#password").val();
	var xingming = $("#xingming").val();
	var bumen = $("#bumen").val();
	var beizhu = $("#beizhu").val();

	if (account == null) {
		$.sticky("账号不能为空！", {
			style : 'error',
			autoclose : 1000,
			position : 'center'
		});
		return;
	}

	$.ajax({
		type : "post",
		data : {
			"account" : account,
			"password" : password,
			"xingming" : xingming,
			"bumen" : bumen,
			"beizhu" : beizhu,
			"editFlag" : editFlag

		},

		url : ctx + "/service/sys/addUser",
		error : function(data) {
			$.sticky("服务器错误！", {
				style : 'error',
				autoclose : 1000,
				position : 'center'
			});
		},
		success : function(data) {
			var saveFlag = data.saveFlag;
			if (saveFlag == "success") {
				$.sticky("添加成功!", {
					style : 'success',
					autoclose : '1000',
					position : 'center'
				});
				parent.location.href = ctx + "/service/sys/userListPage";
			} else if (changeFlag == "error") {
				$.sticky("添加失败!", {
					style : 'error',
					autoclose : 1000,
					position : 'center'
				});

			} else {
				$.sticky("数据库错误!", {
					style : 'error',
					autoclose : 1000,
					position : 'center'
				});
			}

		}
	})

}