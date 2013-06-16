var footer =  {
	ensurediv : function() {
		if(!footer.alertbox || footer.alertbox.length == 0) {
			footer.alertbox = $('#footer-error');
		}
	},
	success : function(msg) {
		footer.ensurediv();
		var span = $('<span class="text-success">').text(msg);
		footer.alertbox.html(span);
	},
	debug : function(msg) {
		footer.ensurediv();
		var span = $('<span>').text(msg);
		footer.alertbox.html(span);
	},
	error : function(msg) {
		footer.ensurediv();
		var span = $('<span class="text-error">').text(msg);
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
				footer.error('Error loading ' + uri + ': ' + xhr.status + '-' + xhr.statusText);
				break;
			default:
				footer.success('Stop the schmucking!');
			}
		});
	}
	
	$(document).on({
		click: function(){
			var uri = $(this).attr('href');
			if(!uri || uri === 'javascript:;' || uri.indexOf('#') == 0 || uri.indexOf('/logout') != -1) {
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