$(function(){

	//search
	var 
	 $input = $('#navbar-search'),
	 $clickery = $('#clickery');
	
	$input.typeahead({
		source: function(query, process) {
			return $.get(navbarUrls.search, {query : query}, function(response) {
				switch(response.status) {
				case '200':
					var items = [];
					$.each(response.data, function(i, dto) {
						switch(dto.type) {
						case 'card':
							
							if(dto.id.indexOf(',') != -1) {
							 items.push('<a href="' + navbarUrls.multicard + dto.id + '">' + dto.name + '</a>');
							} else {
					     items.push('<a href="' + navbarUrls.card + dto.id + '">' + dto.name + '</a>');
							}
							break;
						default:
							items.push(dto.name);
						}
					});
					return process(items);
					break;
				default:
					return process([]);
				}
				return process(response.data);
			});
		},
		updater: function(item) {
			$clickery.html('');
			$(item).appendTo($clickery).click();
		},
		highlighter: function(item) {
			return item;
		}
	});
	
	//highlight the active nav
	var $navs = $('#header-nav li');
	$navs.click(function(){
		$navs.removeClass('active');
		$(this).addClass('active').blur();
	});
});