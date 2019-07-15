$(document).ready(function() {

	// 根据不同操作标识选择保存方法
	var flag = $("#flag").val();
	var url = ctx;
	var cz = "";
	if (flag == "add") {
		cz = "添加";
		url += "/service/nsrgxgl/addGxfl";
	} else if (flag == "update") {
		cz = "修改";
		url += "/service/nsrgxgl/updateGxfl";
	}

	$("#gxflForm").uValidform({
		btnSubmit : "#save",
		callback : function(form) {
			$.ajax({
				url : url,
				type : "post",
				async : false,
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({
					"gxflDm" : $.trim($("#gxflDm").val()),
					"gxflMc" : $.trim($("#gxflMc").val())
				}),
				success : function(data) {
					if (data == true) {
						parent.dialog.get(window).close();
					} else {
						$.sticky(cz + '关系分类失败！', {
							style : 'warning',
							autoclose : 5000,
							position : 'center'
						});
					}
				},
				error : function(e) {
					$.sticky(cz + '关系分类异常！', {
						style : 'warning',
						autoclose : 5000,
						position : 'center'
					});
				}
			});
		}
	});

});
