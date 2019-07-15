var url = ctx + "/service/nsrgxtj/listGxtj";

$(document).ready(function() {

	// 初始化datatable
	var options = {};
	grid = new L.FlexGrid("gxtjList", url);
	grid.init(options);

	$(document).on("click", "#searchBtn", function() {
		search();
	});
	$(document).on("click", "#resetBtn", function() {
		this.form.reset();
	});

});

/**
 * 搜索
 * 
 * @returns
 */
function search() {
	grid.reload(url);
}

/**
 * 内容链接
 * 
 * @param data
 * @param type
 * @param full
 * @returns
 */
function renderLj(data, type, full) {
	data = '<a href="javascript:void(0)" onclick="showGxList()">' + data
			+ '</a>';
	return data;
}

/**
 * 关系清单超链接
 * 
 * @returns
 */
function showGxList() {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/nsrgxtj/toGxLink',
		title : '关系清单',
		width : 1000,
		height : 400
	});
}