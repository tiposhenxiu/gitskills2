$(document).ready(function() {
	var flag = false;
	var pagetop = $('.ptop');
	$(window).scroll(function () {
		if ($(this).scrollTop() > 200) {
			if (flag == false) {
				flag = true;
				pagetop.stop().animate({
					'bottom': '0'
				}, 200);
			}
		} else {
			if (flag) {
				flag = false;
				pagetop.stop().animate({
					'bottom': '-25px'
				}, 200);
			}
		}
	});
	pagetop.click(function () {
		$('body, html').animate({ scrollTop: 0 }, 500);
		return false;
	});
});