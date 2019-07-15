$(document).ready(function() {

	var username = $("#userId").val();
	if(username == "admin"){
		document.getElementById("update").style.display="";	
		document.getElementById("modify").style.display="";	
	}
});
$(function() {

	// 调用左右拖动鼠标调整页面布局功能
	$(".ue-menu-left").dragResize({
		target : ".ue-menu-right",// 被动变化元素，可为空
		handles : "e"// 支持e、s、n、w四个方向
	});

	// initialize menu
	$('.ue-vmenu').vmenu();

	// 美化滚动条
	$('.ue-vmenu').slimScroll({
		height : $('.ue-vmenu').height()
	});

});

function toggleSide() {
	var width = $('.ue-menu-left').width();
	if (!$('.ue-menu-left').data('isClose')) {
		$('.ue-menu-right').css({
			'border-left' : '1px solid #ddd'
		}).animate({
			left : '0px'
		}, "slow");
		$('.ue-menu-left').animate({
			left : '-' + width + 'px'
		}, "slow");
		$('.ue-menu-left').data('isClose', true);
	} else {
		$('.ue-menu-right').css({
			'border-left' : 'none'
		}).animate({
			left : width + 1 + 'px'
		}, "slow");
		$('.ue-menu-left').animate({
			left : '0px'
		}, "slow");
		$('.ue-menu-left').data('isClose', false);
	}
}

function loadUrl(name, url) {
	$('.ue-right-top').text(name);
	$('#mainFrame').attr('src', url);
}

function logout(){
	window.location.href = ctx + "/service/sysLogin/logout";
}
