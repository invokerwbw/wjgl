$(document).ready(function() {

	//日期插件
	$('.input-group.date').datetimepicker({
		container: $("#fbrq"),
		language: "zh-CN",
		autoclose: true,
		minView: 2,
		format: "yyyy-mm-dd"
	}).on("changeDate",function(){
		$(this).find("input").blur();
	});	
	$('.input-group.date').datetimepicker({
		container: $("#ssrq"),
		language: "zh-CN",
		autoclose: true,
		minView: 2,
		format: "yyyy-mm-dd"
	}).on("changeDate",function(){
		$(this).find("input").blur();
	});	
	$('.input-group.date').datetimepicker({
		container: $("#fzrq"),
		language: "zh-CN",
		autoclose: true,
		minView: 2,
		format: "yyyy-mm-dd"
	}).on("changeDate",function(){
		$(this).find("input").blur();
	});	
	
	// 根据不同操作标识选择保存方法
	var flag = $("#flag").val();
	var url = ctx;
	if (flag == "add") {
		url += "/service/modify/insertStd";
	} else if (flag == "update") {
		url += "/service/modify/updateStd";
		$("#bzh").attr("readonly","readonly");
		$("#zt").val($("#ztValue").val());
	}

	$("#stdForm").uValidform({
		btnSubmit : "#save",
		callback : function(form) {
			$.ajax({
				url : url,
				type : "post",
				async : false,
				contentType : "application/json; charset=utf-8",
				data : JSON.stringify({
					"bzh" : $("#bzh").val(),
					"zt" : $("#zt").val(),
					"zwmc" : $("#zwmc").val(),
					"ywmc" : $("#ywmc").val(),
					"fw" : $("#fw").val(),
					"fbrq" : $("#fbrqVal").val(),
					"ssrq" : $("#ssrqVal").val(),
					"fzrq" : $("#fzrqVal").val(),
					"zzh" : $("#zzh").val(),
					"qwys" : $("#qwys").val(),
					"zwyz" : $("#zwyz").val(),
					"dtbz" : $("#dtbz").val(),
					"bdtbz" : $("#bdtbz").val(),
					"cybz" : $("#cybz").val(),
					"xgd" : $("#xgd").val(),
					"qcdw" : $("#qcdw").val(),
					"icsflh" : $("#icsflh").val(),
					"zbflh" : $("#zbflh").val(),
					"gmhyflh" : $("#gmhyflh").val()
				}),
				success : function(data) {
					if (data == true) {
						parent.dialog.get(window).close();
					} else {
						$.sticky('保存失败！', {
							style : 'warning',
							autoclose : 5000,
							position : 'center'
						});
					}
				},
				error : function(e) {
					$.sticky('保存异常！', {
						style : 'warning',
						autoclose : 5000,
						position : 'center'
					});
				}
			});
		}
	});

});
