var url = ctx + "/service/nsrgxgl/listGxlx";
var gxflDm;

$(document).ready(function() {

	gxflDm = $("#gxflDm").val();

	// 初始化datatable
	var options = {};
	grid = new L.FlexGrid("gxlxList", url);
	grid.setParameter("gxflDm", gxflDm);
	grid.init(options);

	// 为操作按钮绑定单击事件
	$(document).on("click", ".addBtn", toAddGxlx);
	$(document).on("click", ".rtnBtn", toGxflList);
	$(document).on("click", ".viewBtn", function() {
		toViewGxlx(this)
	});
	$(document).on("click", ".updateBtn", function() {
		toUpdateGxlx(this)
	});
	$(document).on("click", ".deleteBtn", function() {
		deleteGxlx(this)
	});
	$(document).on("click", ".disabledBtn", function() {
		disabledGxlx(this)
	});
	$(document).on("click", ".enabledBtn", function() {
		enabledGxlx(this)
	});

});

/**
 * 重载
 * 
 * @returns
 */
function reload() {
	var param = {};
	param["gxflDm"] = gxflDm;
	grid.reload(url, param, false);
}

/**
 * 有效标志字符转换
 * 
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function renderYxbz(data, type, full) {
	switch (data) {
	case "Y":
		data = '<span class="fa fa-check-circle text-success">&nbsp;有效</span>';
		break;
	case "N":
		data = '<span class="fa fa-times-circle text-danger">&nbsp;无效</span>';
		break;
	}
	return data;
}

/**
 * 为每行数据添加操作按钮
 * 
 * @param data
 * @param type
 * @param full
 * @returns {String}
 */
function renderCzBtn(data, type, full) {
	var btn = '<button type="button" class="btn btn-xs ue-btn viewBtn"><span class="fa fa-file-text-o"></span>查看</button>';
	btn += '<button type="button" class="btn btn-xs ue-btn updateBtn"><span class="fa fa-pencil"></span>修改</button>';
	btn += '<button type="button" class="btn btn-xs ue-btn deleteBtn"><span class="fa fa-trash"></span>删除</button>';
	if (data == "Y") {
		btn += '<button type="button" class="btn btn-xs ue-btn disabledBtn"><span class="fa fa-lock"></span>停用</button>';
	} else if (data == "N") {
		btn += '<button type="button" class="btn btn-xs ue-btn enabledBtn"><span class="fa fa-unlock"></span>启用</button>';
	}
	return btn;
}

/**
 * 关系类型添加页面
 */
function toAddGxlx() {
	window.location.href = ctx + "/service/nsrgxgl/toAddGxlx/" + gxflDm;
}

/**
 * 关系类型修改页面
 * 
 * @param obj
 */
function toUpdateGxlx(obj) {
	var gxlxBm = grid.oTable.row($(obj).parents("tr")).data().gxlxBm;
	window.location.href = ctx + "/service/nsrgxgl/toUpdateGxlx/" + gxlxBm;
}

/**
 * 删除指定关系类型
 * 
 * @param obj
 */
function deleteGxlx(obj) {
	var gxlxBm = grid.oTable.row($(obj).parents("tr")).data().gxlxBm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定删除该关系类型吗?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/deleteGxlx/' + gxlxBm,
				success : function(data) {
					if (data == true) {
						$.sticky('删除关系类型成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('删除关系类型失败！', {
							style : 'warning',
							autoclose : 5000,
							position : 'center'
						});
					}
					reload();
				}
			});
		},
		cancel : function() {
		}
	});
}

/**
 * 启用指定关系类型
 * 
 * @param obj
 */
function enabledGxlx(obj) {
	var gxlxBm = grid.oTable.row($(obj).parents("tr")).data().gxlxBm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定启用该关系类型吗?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/enabledGxlx/' + gxlxBm,
				success : function(data) {
					if (data == true) {
						$.sticky('启用关系类型成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('启用关系类型失败！', {
							style : 'warning',
							autoclose : 5000,
							position : 'center'
						});
					}
					reload();
				}
			});
		},
		cancel : function() {
		}
	});
}

/**
 * 停用指定关系类型
 * 
 * @param obj
 */
function disabledGxlx(obj) {
	var gxlxBm = grid.oTable.row($(obj).parents("tr")).data().gxlxBm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定停用该关系类型吗?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/disabledGxlx/' + gxlxBm,
				success : function(data) {
					if (data == true) {
						$.sticky('停用关系类型成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('停用关系类型失败！', {
							style : 'warning',
							autoclose : 5000,
							position : 'center'
						});
					}
					reload();
				}
			});
		},
		cancel : function() {
		}
	});
}

/**
 * 跳转至关系分类列表页面
 * 
 * @param obj
 */
function toGxflList(obj) {
	window.location.href = ctx + "/service/nsrgxgl/toGxfl";
}

/**
 * 跳转至关系类型查看页面
 * 
 * @param obj
 * @returns
 */
function toViewGxlx(obj) {
	var gxlxBm = grid.oTable.row($(obj).parents("tr")).data().gxlxBm;
	window.location.href = ctx + "/service/nsrgxgl/toViewGxlx/" + gxlxBm;
}