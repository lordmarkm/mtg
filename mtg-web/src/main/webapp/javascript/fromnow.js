$(function(){
	if($('.fromNow').length === 0) {
		$('#btn-loadmore').hide();
		return;
	} else {
		$('#btn-loadmore').show();
	}
	
	$('.fromNow').each(function(i, span){
		var $span = $(span);
		$span.text(moment($span.text()).fromNow()).removeClass('fromNow');
	});
});