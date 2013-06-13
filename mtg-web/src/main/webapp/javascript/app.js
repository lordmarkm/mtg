$(function(){
	var $load = $('#loadhere')
	
	function go(uri) {
		history.pushState({uri: uri}, null, uri);
		load(uri);
	}
	
	function load(uri) {
		$load.load(uri + '?ajax');
	}
	
	$(document).on({
		click: function(){
			var uri = $(this).attr('href');
			if(uri === 'javascript:;' || uri === '#' || uri.indexOf('/logout') != -1) {
				return;
			}
			go(uri);

			$(document).click();
			return false;
		}
	}, 'a');
	
	if(page.target) {
		go(page.target);
	}
	
	$(window).bind('popstate', function(e) {
		load(e.originalEvent.state.uri);
	});
});