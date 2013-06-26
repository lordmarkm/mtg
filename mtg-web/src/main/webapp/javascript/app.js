var mtgTools = {
	nl2br : function (str, is_xhtml) {
	    var breakTag = (is_xhtml || typeof is_xhtml === 'undefined') ? '<br />' : '<br>';
	    return (str + '').replace(/([^>\r\n]?)(\r\n|\n\r|\r|\n)/g, '$1' + breakTag + '$2');
	}	
}

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

$.ajaxSetup({
	error: function(xhr, status) {
		'Error! status: ' + status;
	}
});

$.fn.loading = function(){
	var loading = $('<div class="loading-modal">').prependTo(this);
	var prog = $('<div class="loading-modal-bar progress progress-striped active">')
		.appendTo(loading);
	$('<div class="bar">').css('width', '100%').appendTo(prog);
}

$.fn.notloading = function(){
	this.find('.loading-modal').remove();
}

var loadhere = {
	ensureElement : function(){
		if(!loadhere.elem || loadhere.elem.length === 0) {
			loadhere.elem = $('#loadhere');
		}
	},
	
	loading : function() {
		loadhere.ensureElement();
		loadhere.timeout = setTimeout(function(){
			loadhere.elem.loading();
		}, 200);
	},
	
	notloading : function() {
		loadhere.ensureElement();
		clearTimeout(loadhere.timeout);
		loadhere.elem.notloading();
	}
}

$(function(){
	var 
		$loadcontainer = $('#loadhere-container'), //lol loadcontainer
		$load = $('#loadhere'),
		$error = $('#footer-error'),
		$navs = $('#header-nav li');
	
	function go(uri) {
		load(uri, true);
	}
	
	function load(uri, push) {
		//no need to show loading div if server returns really fast
		var loadingtimeout = setTimeout(function(){
		  $loadcontainer.loading();
		}, 200);
		
		$load.load(uri + '?ajax', function(response, status, xhr) {
			switch(status) {
			case 'error':
				footer.error('Error loading ' + uri + ': ' + xhr.status + '-' + xhr.statusText);
				break;
			default:
				window.scrollTo(0,0);
				footer.success('Stop the schmucking!');
				if(push) {
					history.pushState({uri: uri}, null, uri);
				}
				
				$navs.removeClass('active');
				var activeNavbarClass = $('#active-navbar-class').text();
				if(activeNavbarClass) {
					$('#header-nav li.' + activeNavbarClass).addClass('active');
				}
			}
			clearTimeout(loadingtimeout);
			$loadcontainer.notloading();
		});
	}
	
	$(document).on({
		click: function(){
			var a = $(this);
			
			if(a.attr('target') === '_blank') {
				return;
			}
			
			if(a.hasClass('no-intercept')) {
				return;
			}
			
			var uri = a.attr('href');
			if(!uri || uri === 'javascript:;' || uri.indexOf('#') == 0) {//mostly for apis
				return;
			}
			go(uri);

			$(document).click();
			return false;
		}
	}, 'a');
	
	if(page.target) {
		go(page.target);
	} else {
		go(page.welcome);
	}
	
	$(window).bind('popstate', function(e) {
		load(e.originalEvent.state.uri, false);
	});
});