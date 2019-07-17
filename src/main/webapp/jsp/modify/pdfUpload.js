$(function() {
	var $r = $('#rpick');
	var uploader = new plupload.Uploader({
		runtimes: 'html5,flash,silverlight,html4',
		browse_button: 'rpickfiles',
		multi_selection: false,
		url: ctx + "/service/modify/upload?bzh=" + $("#bzh").val(),
		max_file_size: '50mb',
        unique_names: true,
        filters: [
             {title: "pdf", extensions: "pdf"}
        ],
        flash_swf_url: ctx + '/skins/js/Moxie.swf',
        silverlight_xap_url: ctx + '/skins/js/Moxie.xap',
        init: {
			FilesAdded: function(up, files) {
				//上传时的加载效果
				$r.loading({
					isShowMask: true,
					lines: 10,
					length: 8,
					width: 5,
					radius: 15
				});
				uploader.start();
			},
			UploadProgress: function(up, file) {
				if (file.percent == 100) {
					$r.unloading();//上传完毕
					document.getElementById('rresult').innerHTML = '上传成功！';
					parent.dialog.get(window).close();
				}
			},
			//当发生错误时触发
			Error: function(up, err) {
				document.getElementById('rresult').appendChild(document.createTextNode("\nError #" + err.code + ": " + err.message));
			}
		}
	});
	//初始化Plupload实例
	uploader.init();
});
