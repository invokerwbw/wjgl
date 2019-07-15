var outer;

$(document).ready(function() {

	outer = $("#outer");

	$("input[type='file']").change(function() {
		$('#showInputFile').val($(this).val());
	});

	$(document).on("click", "#uploadBtn", function() {
		importFile();
	});

});

function importFile() {

	var filePath = $("#importFile").val();

	if (filePath == "") {
		$.dialog({
			type : 'confirm',
			content : '请选择要上传的文件',
			ok : function() {
			},
			cancel : false
		});
		return;
	} else {
		var fileExt = filePath.substring(filePath.lastIndexOf("."))
				.toLowerCase();
		if (!fileExt.match(/.sql/i)) {
			$.dialog({
				type : 'confirm',
				content : '请选择sql文件',
				ok : function() {
				},
				cancel : false
			});
			return;
		}
	}

	var opt = {
		type : 'post',
		async : true,
		dataType : 'json',
		url : ctx + "/service/update/importSql",
		beforeSubmit : function() {
			outer.loading({
				isShowMask : true,
				lines : 8,
				length : 0,
				width : 10,
				radius : 15,
				maskOpacity : '0.4',
				loadingText : '正在执行...'
			});
		},
		success : function(data) {
			outer.unloading();
			$.dialog({
				type : 'confirm',
				content : data.message,
				ok : function() {
				},
				cancel : false
			});
		},
		error : function(e) {
			outer.unloading();
			$.dialog({
				type : 'confirm',
				content : '导入失败' + e,
				ok : function() {
				},
				cancel : false
			});
		}

	};

	// ajax提交form
	$("#excelForm").ajaxSubmit(opt);
}