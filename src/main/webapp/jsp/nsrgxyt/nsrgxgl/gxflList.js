var url = ctx + "/service/nsrgxgl/listGxfl";

$(document).ready(function() {

	// 初始化datatable
	var options = {};
	grid = new L.FlexGrid("gxflList", url);
	grid.init(options);

	// 为操作按钮绑定单击事件
	$(document).on("click", ".addBtn", toAddGxfl);
	$(document).on("click", ".gxlxBtn", function() {
		toGxlxList(this)
	});
	$(document).on("click", ".updateBtn", function() {
		toUpdateGxfl(this)
	});
	$(document).on("click", ".deleteBtn", function() {
		deleteGxfl(this)
	});
	$(document).on("click", ".disabledBtn", function() {
		disabledGxfl(this)
	});
	$(document).on("click", ".enabledBtn", function() {
		enabledGxfl(this)
	});

});

/**
 * 重载
 * 
 * @returns
 */
function reload() {
	var param = {};
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
	var btn = '<button type="button" class="btn btn-xs ue-btn gxlxBtn"><span class="fa fa-cog"></span>关系类型</button>';
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
 * 关系分类添加页面
 */
function toAddGxfl() {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/nsrgxgl/toAddGxfl',
		title : '添加关系分类',
		width : 900,
		height : 170,
		onclose : function() {
			reload();
		}
	});
}

/**
 * 关系分类修改页面
 * 
 * @param obj
 */
function toUpdateGxfl(obj) {
	var gxflDm = grid.oTable.row($(obj).parents("tr")).data().gxflDm;
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/nsrgxgl/toUpdateGxfl/' + gxflDm,
		title : '修改关系分类',
		width : 900,
		height : 170,
		onclose : function() {
			reload();
		}
	});
}

/**
 * 删除指定关系分类
 * 
 * @param obj
 */
function deleteGxfl(obj) {
	var gxflDm = grid.oTable.row($(obj).parents("tr")).data().gxflDm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定删除该关系分类吗（该分类下的关系类型也会被删除）?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/deleteGxfl/' + gxflDm,
				success : function(data) {
					if (data == true) {
						$.sticky('删除关系分类成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('删除关系分类失败！', {
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
 * 启用指定关系分类
 * 
 * @param obj
 */
function enabledGxfl(obj) {
	var gxflDm = grid.oTable.row($(obj).parents("tr")).data().gxflDm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定启用该关系分类吗（该分类下的关系类型也会被启用）?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/enabledGxfl/' + gxflDm,
				success : function(data) {
					if (data == true) {
						$.sticky('启用关系分类成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('启用关系分类失败！', {
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
 * 停用指定关系分类
 * 
 * @param obj
 */
function disabledGxfl(obj) {
	var gxflDm = grid.oTable.row($(obj).parents("tr")).data().gxflDm;
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定停用该关系分类吗（该分类下的关系类型也会被停用）?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/nsrgxgl/disabledGxfl/' + gxflDm,
				success : function(data) {
					if (data == true) {
						$.sticky('停用关系分类成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('停用关系分类失败！', {
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
 * 跳转至关系类型列表页面
 * 
 * @param obj
 */
function toGxlxList(obj) {
	var gxflDm = grid.oTable.row($(obj).parents("tr")).data().gxflDm;
	window.location.href = ctx + "/service/nsrgxgl/toGxlx/" + gxflDm;
}