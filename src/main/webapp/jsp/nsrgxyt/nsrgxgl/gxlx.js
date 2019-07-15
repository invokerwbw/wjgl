var zdGrid; // 字段grid
var gxflDm;// 关系分类代码

// grid配置
var options = {
	paging : false,
	info : false
};

$(document).ready(function() {

	gxflDm = $("#gxflDm").val();

	init();

	$(document).on("click", ".rtnBtn", function() {
		window.location.href = ctx + "/service/nsrgxgl/toGxlx/" + gxflDm;
	});

	// 初始化validform
	$("#gxlxForm").uValidform({
		btnSubmit : "#save",
		callback : function(form) {
			saveGxlx();
		}
	});

});

/**
 * 初始化
 */
function init() {

	$('#gxlxFszqqDiv').datetimepicker({
		container : $("#gxlxFszqstart"),
		language : "zh-CN",
		autoclose : 1,
		startView : 2,
		minView : 2,
		format : "yyyy-mm-dd"
	});
	$('#gxlxFszqzDiv').datetimepicker({
		container : $("#gxlxFszqend"),
		language : "zh-CN",
		autoclose : 1,
		startView : 2,
		minView : 2,
		format : "yyyy-mm-dd"
	});

	var gxlxYxjVal = $("#gxlxYxjVal").val();
	if (gxlxYxjVal != undefined && gxlxYxjVal != '') {
		$("#gxlxYxj").val(gxlxYxjVal);
	}

	var gxlxBm = $("#gxlxBm").val();
	initZdGrid(gxlxBm);

}

/**
 * 初始化字段grid
 * 
 * @param uuid
 */
function initZdGrid(gxlxBm) {
	var url = ctx + "/service/nsrgxgl/listZd";
	zdGrid = new L.EditGrid("zdList", url);
	if (gxlxBm != undefined && gxlxBm != '') {
		zdGrid.setParameter("gxlxBm", gxlxBm);
	}
	zdGrid.init(options);
}

/**
 * 是否下拉列表值
 * 
 * @returns {Array}
 */
function getSfSelectVal() {
	var arr = [];
	arr.push({
		key : 0,
		value : "否"
	});
	arr.push({
		key : 1,
		value : "是"
	});
	return arr;
}

/**
 * 是否显示内容转换
 * 
 * @param row
 * @param cell
 * @param value
 * @param columnDef
 * @param dataContext
 * @returns {String}
 */
function renderSf(row, cell, value, columnDef, dataContext) {
	switch (value) {
	case "0":
		value = "否";
		break;
	case "1":
		value = "是";
		break;
	default:
		value = "";
	}
	return value;
}

/**
 * 字段grid添加行
 */
function zdAddRow() {
	var zdData = zdGrid.getData();

	var result = checkZd(zdData);
	if (result.valid) {
		zdGrid.addRow({
			zszyzd : '0',
			jszd : '0',
			gzpdzd : '0'
		});
	} else {
		$.dialog({
			type : "alert",
			content : result.msg
		});
	}
}

/**
 * 字段grid删除行
 */
function zdDelRow() {
	zdGrid.deleteRow();
}

/**
 * 是否符合规则的名称
 * 
 * @param value
 * @returns
 */
function isName(value) {
	var regx = /(^_([a-zA-Z0-9]_?)*$)|(^[a-zA-Z](_?[a-zA-Z0-9])*_?$)/;
	if (!regx.test(value)) {
		return {
			valid : false,
			msg : '1.首位可以是字母以及下划线。\n2.首位之后可以是字母，数字以及下划线。\n3.下划线后不能接下划线。'
		};
	}
	return {
		valid : true,
		msg : null
	};
}

/**
 * 中文、英文、数字、下划线及英文状态下的逗号、句号、冒号
 * 
 * @param value
 * @returns
 */
function isLegal(value) {
	var regx = /^[\u4E00-\u9FA5A-Za-z0-9_]+$/;
	if (!regx.test(value)) {
		return {
			valid : false,
			msg : '只能输入中文、英文、数字、下划线'
		};
	}
	return {
		valid : true,
		msg : null
	};
}

/**
 * 检查字段grid
 * 
 * @param zdData
 * @returns
 */
function checkZd(zdData) {
	var length = zdData.length;
	var data;
	var index;
	var zdmc = [];
	if (length > 0) {
		for (var i = 0; i < length; i++) {
			data = zdData[i];
			index = i + 1;
			if (!data.zdmc) {
				return {
					valid : false,
					msg : "请输入第" + index + "行的字段名称"
				};
			}
			zdmc.push(data.zdmc);
			if (!data.xsmc) {
				return {
					valid : false,
					msg : "请输入第" + index + "行的显示名称"
				};
			}
			if (!data.zszyzd) {
				return {
					valid : false,
					msg : "请选择第" + index + "行的是否展示重要字段"
				};
			}
			if (!data.jszd) {
				return {
					valid : false,
					msg : "请选择第" + index + "行的是否检索字段"
				};
			}
			if (!data.gzpdzd) {
				return {
					valid : false,
					msg : "请选择第" + index + "行的是否规则判断字段"
				};
			}
		}
		if (zdmc.length > 0) {
			for (var m = 0; m < zdmc.length; m++) {
				for (var n = m + 1; n < zdmc.length; n++) {
					if (zdmc[m] == zdmc[n]) {
						return {
							valid : false,
							msg : "第" + (m + 1) + "行与第" + (n + 1) + "行的字段名称相同"
						};
					}
				}
			}
		}
	}
	return {
		valid : true,
		msg : null
	};
}

/**
 * 保存关系类型
 * 
 * @returns
 */
function saveGxlx() {

	var gxlxFszqq = $.trim($("#gxlxFszqq").val());
	if (gxlxFszqq == '') {
		$.dialog({
			type : "alert",
			content : "请选择关系类型发生周期起"
		});
		return;
	}

	var gxlxFszqz = $.trim($("#gxlxFszqz").val());
	if (gxlxFszqz == '') {
		$.dialog({
			type : "alert",
			content : "请选择关系类型发生周期止"
		});
		return;
	}

	var zdData = zdGrid.getData();
	var result = checkZd(zdData);
	if (zdData.length == 0) {
		$.dialog({
			type : "alert",
			content : "请至少添加一条字段信息"
		});
		return;
	}
	if (!result.valid) {
		$.dialog({
			type : "alert",
			content : result.msg
		});
		return;
	}

	// 根据不同操作标识选择保存方法
	var flag = $("#flag").val();
	var url = ctx;
	var cz = "";
	if (flag == "add") {
		cz = "添加";
		url += "/service/nsrgxgl/addGxlx";
	} else if (flag == "update") {
		cz = "修改";
		url += "/service/nsrgxgl/updateGxlx";
	}

	// 进行提交
	$.ajax({
		url : url,
		type : "post",
		async : false,
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify({
			"gxflDm" : gxflDm,
			"gxlxBm" : $.trim($("#gxlxBm").val()),
			"gxlxMc" : $.trim($("#gxlxMc").val()),
			"gxlxLyb" : $.trim($("#gxlxLyb").val()),
			"gxlxYxj" : $.trim($("#gxlxYxj").val()),
			"gxlxFszqq" : gxlxFszqq,
			"gxlxFszqz" : gxlxFszqz,
			"gxlxZd" : zdData
		}),
		success : function(data) {
			if (data.flag == true) {
				$.sticky(cz + '成功！', {
					style : 'success',
					autoclose : 5000,
					position : 'center'
				});
				window.location.href = ctx + "/service/nsrgxgl/toViewGxlx/"
						+ data.gxlxBm;
			} else {
				$.sticky(cz + '失败！', {
					style : 'warning',
					autoclose : 5000,
					position : 'center'
				});
			}
		},
		error : function(e) {
			$.sticky(cz + '异常！' + e, {
				style : 'warning',
				autoclose : 5000,
				position : 'center'
			});
		}
	});
}