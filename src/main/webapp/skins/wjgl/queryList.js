var grid;
$(document).ready(function() {

	$("#xianxing").prop("checked", true);
	$("#weishengxiao").prop("checked", true);

	var status = '';
	$.each($('input:checkbox:checked'), function() {
		if (status == '') {
			status += $(this).val();
		} else {
			status += ',' + $(this).val();
		}
	});

	$('#fbrqqDiv').datetimepicker({
		container : $("#fbrqstart"),
		language : "zh-CN",
		autoclose : 1,
		startView : 2,
		minView : 2,
		format : "yyyy-mm-dd"
	});
	$('#fbrqzDiv').datetimepicker({
		container : $("#fbrqend"),
		language : "zh-CN",
		autoclose : 1,
		startView : 2,
		minView : 2,
		format : "yyyy-mm-dd"
	});

	var options = {iDisplayLength:25};

	var url = ctx + "/service/search/listStd";
	grid = new L.FlexGrid("stdList", url);
	grid.setParameter("status", status);
	grid.init(options);

	groupStdInit();
	
	$("#searchBtn").bind("click", function() {

		var bzzz = $("#bzzz").val();
		var keyword = $("#keyword").val();
		var status = '';
		$.each($('input:checkbox:checked'), function() {
			if (status == '') {
				status += $(this).val();
			} else {
				status += ',' + $(this).val();
			}
		});

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
});

function groupStdInit(){
	var status = '';
	$.each($('input:checkbox:checked'), function() {
		if (status == '') {
			status += $(this).val();
		} else {
			status += ',' + $(this).val();
		}
	});
	
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
	var status = '';
	$.each($('input:checkbox:checked'), function() {
		if (status == '') {
			status += $(this).val();
		} else {
			status += ',' + $(this).val();
		}
	});
	grid.setParameter("bzzz", bzzz);
	grid.setParameter("keyword", keyword);
	grid.setParameter("status", status);
	grid.setParameter("leibie",leibie);
	grid.reload(ctx + "/service/search/listStd");
}
function renderBzmc(data, type, full) {
	var bzmc = data.split("brflag");
	var zwmc = $.trim(bzmc[0]);
	var ywmc = $.trim(bzmc[1]);

	if ((zwmc == "" || zwmc == "null") && ywmc != "" && ywmc != "null") {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh='
				+ full.cz + '" target="_Blank"><b><p style="font-size:13px;">' + ywmc + '</p></b></a>';
	} else if (zwmc != "" && zwmc != "null" && (ywmc == "" || ywmc == "null")) {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh='
				+ full.cz + '" target="_Blank"><b>' + zwmc + '</b></a>';
	} else {
		data = '<a href="' + ctx + '/service/fileOperate/stdInfo?bzh='
				+ full.cz
				+ '" target="_Blank"><b><p style="font-size:13px;">' + zwmc
				+ '</p></b></a>';
	}
	return data;
}


function renderCz(data, type, full) {
	var bzh = data;
	
	data = '<a href="javascript:readPdf(' + '\'' + bzh + '\'' + ');">' + '预览'
			+ '</a>';
	data = data + ' | <a href="javascript:downLoadPDF(' + '\'' + bzh + '\''
			+ ');">' + '下载' + '</a>';
	return data;
}

function downLoadPDF(bzh) {
	if (bzh != undefined && bzh != null && bzh != '') {
		$.ajax({
			url : ctx + "/service/fileOperate/checkPDFIsExist?bzh=" + bzh,
			dataType : 'json',
			success : function(data) {
				if (data.flag == false) {
					$.dialog({
						type : 'confirm',
						content : data.message,
						ok : function() {
						},
						cancel : false
					});
				} else {
					window.location.href = ctx
							+ "/service/fileOperate/download?bzh=" + bzh;
				}
			}
		});
	}

}

function readPdf(bzh) {
	if (bzh != undefined && bzh != null && bzh != '') {
		$.ajax({
			url : ctx + "/service/fileOperate/checkPDFIsExist?bzh=" + bzh,
			dataType : 'json',
			success : function(data) {
				if (data.flag == false) {
					$.dialog({
						type : 'confirm',
						content : data.message,
						ok : function() {
						},
						cancel : false
					});
				} else {
					window.open(
							ctx + "/service/fileOperate/readPdf?bzh=" + bzh,
							"_blank");
				}
			}
		});
	}
}