$(function(){
	var $wantedContainer = $('#wantlist-container');
	function reload() {
		loadhere.loading();
		$wantedContainer.load(dashboardUrls.wantlist, function(){
			loadhere.notloading();
		});
	}
	
	//increment, decrement, delete
	$('.wanted-operation').on({
		click: function(){
			var $that = $(this).attr('disabled', 'disabled').addClass('disabled');
			var id = $that.closest('tr').attr('wanted-id');
			var op = $that.attr('wanted-op');
			
			$.post(dashboardUrls.wantedOp + op + '/' + id, function(response) {
				switch(response.status) {
				case '200':
					reloadTr($that, response.wanted);
					break;
				default:
					footer.error('Error with ' + op + '!');
				}
			}).complete(function(){
				$that.removeAttr('disabled').removeClass('disabled');
			});
		}
	});
	
	$('.wanted-editnote').on({
		click : function() {
			var $that = $(this).attr('disabled', 'disabled').addClass('disabled');
			var id = $that.closest('tr').attr('wanted-id');
			bootbox.prompt('Enter note for wanted card', function(result){
				if(null === result) {
					$that.removeAttr('disabled').removeClass('disabled');
					return;
				}
				$.post(dashboardUrls.editWantedNote + id, {note:result}, function(response){
					switch(response.status){
					case '200':
						reloadTr($that, response.wanted);
						break;
					default:
						footer.error('Error editing note!');
					}
				});
			});
		}
	});
	
	function reloadTr($link, wanted) {
		var $tr = $link.closest('tr');
		$tr
			.find('.wanted-count').html(wanted.count).end()
			.find('.wanted-note').html(wanted.note);
	}
});