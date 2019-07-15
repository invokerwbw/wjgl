var url = ctx + "/service/nsrgxtj/listGx";

$(document).ready(function() {

	// 初始化datatable
	var options = {};
	grid = new L.FlexGrid("gxList", url);
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
	grid.setParameter("nsrsbh", $("#nsrsbh").val());
	grid.setParameter("gxNsrsbh", $("#gxNsrsbh").val());
	grid.setParameter("gxxlDm", $("#gxxlDm").val());
	grid.reload(url);
}