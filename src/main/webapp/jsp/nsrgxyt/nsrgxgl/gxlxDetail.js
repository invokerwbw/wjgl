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

});

/**
 * 初始化
 */
function init() {

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
