$(function(){

	//cards tables
	var $cardselect = $('#cards-expansion-select'),
		$cardstable = $('#cards-table'),
		$pickmessage = $('#cards-pickmessage');

	var $dt;
	
	$cardselect.change(function(){
		if(!$dt) {
			$cardstable.show();
			$pickmessage.hide();
			$dt = $cardstable.dataTable({
				"sDom": "fptir",
				'bProcessing' : true,
				'bServerSide' : true,
				'sAjaxSource' : ajaxSource(),
				'fnServerData': function(sSource, aoData, fnCallback) {
					$.ajax({
						'dataType' : 'json',
						'type'     : 'GET',
						'url'      : sSource,
						'data'     : aoData,
						'success'  : fnCallback
					});
				},
				'aoColumnDefs'     : [
				                      //hide most columns
				                      {'bVisible':false, 'aTargets':[0,3,4]},
				                      
				                      //show Card Name, on click display card
				                      {
				                    	  'fnRender': function(td) {
				                    		  return '<a class="showcard" card-id="' + td.aData[0] + '"href="javascript:;">' 
				                    		  	+ td.aData[1] + '</a>';
				                    	  },
				                    	  'aTargets' : [1]
				                      },
				                      
				                      //actions
				                      {
				                    	  'sWidth' : '10px',
				                    	  'bSortable' : false,
				                    	  'fnRender': function(td) {
				                    		  return '<button card-id="' + td.aData[0] + '" class="btn binder-add" title="Add to binder"><i class="icon-plus"></i></button>';
				                    	  },
				                    	  'aTargets' : [2]
				                      }
				                      ]
			});
			$('.dataTables_filter input').attr("placeholder", "Search within collection");
		} else {
			$dt.fnReloadAjax(ajaxSource());
		}
	});
	
	function ajaxSource() {
		return editbinderUrls.cardsDt + $cardselect.val() + '?ajax';
	}
	
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
	$(document).on({
		click : function(){
			var page = parseInt($page.val());
			var id = $(this).attr('card-id');
			
			$.post(editbinderUrls.addCard + editbinder.binder + '/' + page + '/' + id, function(response) {
				switch(response.status) {
				case '200':
					loadBinderPage(page);
					footer.success('Success!');
					break;
				default:
					footer.error('Error!');
				}
			});
		}
	}, '.btn.binder-add');
});