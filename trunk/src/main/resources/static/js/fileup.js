$(function(){
	fileUploader();
});
var fileUploader = function(){
	var target = $('.fileUploder');
	
	target.each(function(){
		var txt = $(this).find('.filetxt');
		var btn = $(this).find('.filebtn');
		var uploader = $(this).find('.uploader');

		uploader.bind('change',function(){
			txt.val($(this).val());
		});

		btn.bind('click',function(event){
			event.preventDefault();
			return false;
		});
		
		//ホバー処理（非推奨）
		$(this).bind('mouseover',function(){
			btn.css('background-position','0 100%');
		});
		$(this).bind('mouseout',function(){
			btn.css('background-position','0 0');
		});
		
	});
	
}