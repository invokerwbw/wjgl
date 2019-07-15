var url = ctx + "/service/nsrgxtj/listGx";

$(document).ready(function() {

	// 初始化datatable
	var options = {};
	grid = new L.FlexGrid("gxLinkList", url);
	grid.setParameter("gxxlDm", '01001');
	grid.init(options);

});