$(function(){
	//navigate binder
	var 
		$page = $('#select-binder-page'),
		$nextPage = $('#btn-binder-nextpage'),
		$prevPage = $('#btn-binder-prevpage'),
		$table = $('#page-table-container');
	
	function loadBinderPage(page) {
		loadhere.loading();
		
		if(!page) page = 1;
		if(page === 1) {
			$prevPage.addClass('disabled');
			$nextPage.removeClass('disabled');
		} else if(page === 20) {
			$nextPage.addClass('disabled');
			$prevPage.removeClass('disabled');
		} else {
			$prevPage.removeClass('disabled');
			$nextPage.removeClass('disabled');
		}
		
		$table.load(editbinderUrls.getPage + editbinder.binder + '/' + page + '?ajax', function(response, status, xhr) {
			switch(status) {
			case 'error':
				footer.error('Error loading ' + uri + ': ' + xhr.status + '-' + xhr.statusText);
				break;
			}
			loadhere.notloading();
		});
	}
	
	$page.change(function(){
		var page = parseInt($(this).val());
		loadBinderPage(page);
	});
	
	$nextPage.click(function(){
		if($(this).hasClass('disabled')) return false;
		var page = parseInt($page.val()) + 1;
		$page.val(page);
		loadBinderPage(page);
	});
	
	$prevPage.click(function(){
		if($(this).hasClass('disabled')) return false;
		var page = parseInt($page.val()) - 1;
		$page.val(page);
		loadBinderPage(page);
	});
	
	//load page 1 on page load
	loadBinderPage();
	
	//add card to binder
	$('#cards-table').on({
		click : function(){
			var page = parseInt($page.val());
			var id = $(this).attr('card-id');
			
			$.post(editbinderUrls.addCard + editbinder.binder + '/' + page + '/' + id, function(response) {
				switch(response.status) {
				case '200':
					loadBinderPage(page);
					footer.success(response.message);
					break;
				case '500':
					footer.error(response.message);
					break;
				default:
					footer.error('Error!');
				}
			});
		}
	}, '.btn.binder-add');
	
	//increment, decrement, delete bundle
	$table.on({
		click: function(){
			var $bundle = $(this).attr('disabled', 'disabled').addClass('disabled');
			var op = $bundle.attr('bundle-op');
			var id = $bundle.closest('tr').attr('bundle-id');
			var page = parseInt($page.val());
			$.post(editbinderUrls.bundleOp + op + '/' + id, function(response) {
				switch(response.status) {
				case '200':
					loadBinderPage(page);
					break;
				default:
					footer.error('Error!');
				}
			});
		}
	}, '.bundle-operation');

	//edit note
	$table.on({
		click: function(){
			var $btn = $(this).attr('disabled', 'disabled').addClass('disabled');
			var id = $btn.closest('tr').attr('bundle-id');
			var page = parseInt($page.val());
			var note = $btn.attr('title');
			bootbox.prompt('Edit note for card slot', 'Cancel', 'Update note', function(result) {
				if(null == result) {
					$btn.removeAttr('disabled').removeClass('disabled');
					return;
				}
				$.post(editbinderUrls.editBundleNote + id, {note:result}, function(response){
					switch(response.status){
					case '200':
						loadBinderPage(page);
						break;
					default:
						footer.error('Error editing note!');
					}
				});
			}, note);
		}
	}, '.edit-note');
});