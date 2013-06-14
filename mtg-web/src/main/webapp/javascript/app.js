var footer =  {
	ensurediv : function() {
		if(!footer.alertbox || footer.alertbox.length == 0) {
			footer.alertbox = $('#footer-error');
		}
	},
	debug : function(msg) {
		footer.ensurediv();
		var span = $('<span>').text(msg);
		footer.alertbox.html(span);
	},
	error : function(msg) {
		footer.ensurediv();
		var span = $('<span style="color:rgb(185, 74, 72);">').text(msg);
		footer.alertbox.html(span);
	}
}

$(function(){
	var 
		$load = $('#loadhere'),
		$error = $('#footer-error');
	
	function go(uri) {
		history.pushState({uri: uri}, null, uri);
		load(uri);
	}
	
	function load(uri) {
		$load.load(uri + '?ajax', function(response, status, xhr) {
			switch(status) {
			case 'error':
				$error.text('Error loading ' + uri + ': ' + xhr.status + '-' + xhr.statusText);
				break;
			default:
				$error.text('Stop the schmucking!');
			}
		});
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