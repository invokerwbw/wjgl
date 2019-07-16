var grid;
var options = {iDisplayLength:25};
var url = ctx + "/service/modify/listData";
$(document).ready(function() {

	$("#xianxing").prop("checked", true);
	$("#weishengxiao").prop("checked", true);

	var status = getStatus();

	grid = new L.FlexGrid("dataList", url);
	grid.setParameter("status", status);
	grid.init(options);

	groupStdInit();
	
	$("#searchBtn").bind("click", function() {
		var bzzz = $("#bzzz").val();
		var keyword = $("#keyword").val();
		var status = getStatus();

		$.ajax({
			type: "POST",   
		    url : ctx + "/service/search/groupStd",
		    data:"{\"status\":\""+status+"\",\"keyword\":\""+keyword+"\",\"bzzz\":\""+bzzz+"\"}",
		    datatype:"json", 
		    contentType: "application/json; charset=utf-8",
		    success:function(data){ 
		        var groupMap = data.groupMap;
		        $("#all").html("全部("+groupMap.all+")");
		        $("#gb").html("国标("+groupMap.gb+")");
		        $("#hb").html("行标("+groupMap.hb+")");
		        $("#db").html("地标("+groupMap.db+")");
		        $("#tb").html("团标("+groupMap.tb+")");
		        $("#gw").html("国际国外("+groupMap.gw+")");
		    },   
		    error:function(data){
		    }   
		}); 
		
		grid.setParameter("bzzz", bzzz);
		grid.setParameter("keyword", keyword);
		grid.setParameter("status", status);
		grid.reload(url);
	});
	
	$("#resetBtn").bind("click", function() {
		$(".form-inline")[0].reset();
		$("#xianxing").prop("checked", true);
		$("#weishengxiao").prop("checked", true);
	});
	
	$(document).on("click", ".addBtn", toAddStd);
	
});

function getStatus(){
	var status = '';
	$.each($('input:checkbox:checked'), function() {
		if (status == '') {
			status += $(this).val();
		} else {
			status += ',' + $(this).val();
		}
	});
	return status;
}

function groupStdInit(){
	var status = getStatus();
	
	$.ajax({   
	    type: "POST",   
	    url : ctx + "/service/search/groupStd",
	    data:"{\"status\":\""+status+"\"}",
	    datatype:"json",
	    contentType: "application/json; charset=utf-8",
	    success:function(data){
	    	var groupMap = data.groupMap;
	        $("#all").html("全部("+groupMap.all+")");
	        $("#gb").html("国标("+groupMap.gb+")");
	        $("#hb").html("行标("+groupMap.hb+")");
	        $("#db").html("地标("+groupMap.db+")");
	        $("#tb").html("团标("+groupMap.tb+")");
	        $("#gw").html("国际国外("+groupMap.gw+")");
	    },   
	    error:function(data){
	    }
	}); 

}

function groupList(leibie){
	var bzzz = $("#bzzz").val();
	var keyword = $("#keyword").val();
	var status = getStatus();
	
	grid.setParameter("bzzz", bzzz);
	grid.setParameter("keyword", keyword);
	grid.setParameter("status", status);
	grid.setParameter("leibie",leibie);
	grid.reload(url);
}

function renderBzmc(data, type, full) {
	var bzmc = data.split("brflag");
	var zwmc = $.trim(bzmc[0]);
	var ywmc = $.trim(bzmc[1]);

	if ((zwmc == "" || zwmc == "null") && ywmc != "" && ywmc != "null") {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh=' + full.cz + '" target="_Blank"><b><p style="font-size:13px;">' + ywmc + '</p></b></a>';
	} else if (zwmc != "" && zwmc != "null" && (ywmc == "" || ywmc == "null")) {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh=' + full.cz + '" target="_Blank"><b>' + zwmc + '</b></a>';
	} else {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh=' + full.cz + '" target="_Blank"><b><p style="font-size:13px;">' + zwmc + '</p></b></a>';
	}
	
	return data;
}

function renderCz(data, type, full) {
	var bzh = data;
	data = '<a href="javascript:toUpdateStd(' + '\'' + bzh + '\'' + ');">' + '修改' + '</a>';
    data = data + ' | <a href="javascript:deleteStd(' + '\'' + bzh + '\''	+ ');">' + '删除' + '</a>';
    return data;
}

/**
 * 重载
 * 
 * @returns
 */
function reload() {
	var param = {};
	grid.reload(url, param, false);
}

function toAddStd() {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/modify/toAddStd',
		title : '标准维护',
		width : 800,
		height : 600,
		onclose : function() {
			reload();
		}
	});
}

function toUpdateStd(bzh) {
	$.dialog({
		type : 'iframe',
		url : ctx + '/service/modify/toUpdateStd?bzh=' + bzh,
		title : '标准维护',
		width : 800,
		height : 600,
		onclose : function() {
			reload();
		}
	});
}

function deleteStd(bzh) {
	$.dialog({
		title : "",
		type : 'confirm',
		content : '确定删除该标准吗?',
		ok : function() {
			$.ajax({
				type : "post",
				url : ctx + '/service/modify/deleteStd?bzh=' + bzh,
				success : function(data) {
					if (data == true) {
						$.sticky('删除成功！', {
							style : 'success',
							autoclose : 5000,
							position : 'center'
						});
					} else {
						$.sticky('删除失败！', {
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