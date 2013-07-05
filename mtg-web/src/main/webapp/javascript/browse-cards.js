$(function(){
	var 
	  $expSelect = $('#select-expansion'),
	  $dt;
	
	$expSelect.change(function(){
	  $dt.fnReloadAjax(ajaxSource());    
	});
	
	function ajaxSource() {
		var exp = $expSelect.val();
		switch(exp) {
		case 'all':
			return cardBrowseUrls.cardsDt + '?ajax';
			break;
		default:
			return cardBrowseUrls.cardsDt + exp + '?ajax';
		}
	}
	
	var tableInitialized = false;
	$dt = $('#all-the-cards-table').dataTable({
		sDom           : 'pftlir',
		bProcessing    : true,
		bServerSide    : true,
		sAjaxSource    : ajaxSource(),
		fnDrawCallback : function() {
			if(!tableInitialized) {
				//pull expansion dropdown into the table containers
				$('#select-expansion').prependTo('#all-the-cards-table_wrapper');
				
				//set filter placeholder text
				$('#all-the-cards-table_filter input').attr('placeholder', 'Filter by card name');
			}
			tableInitialized = true;
		},
		fnServerData : function(sSource, aoData, fnCallback) {
		    $.ajax({
		        'dataType' : 'json',
		        'type'     : 'GET',
		        'url'      : sSource,
		        'data'     : aoData,
		        'success'  : fnCallback
		    });			
		},
		aoColumnDefs   : [//[id, name, expansion, cost, rarity]
		                  {bVisible: false, aTargets: [0]},
		                  {
		                	  fnRender: function(td){
                          return '<a href="' + cardBrowseUrls.card + td.aData[0] + '" target="_blank">' + td.aData[1] + '</a>';
		                		},
		                		aTargets: [1]
		                  },
		                  {bSortable: false, aTargets: [4]},
		                  
		                  //actions column
		                  {
		                	  aTargets: [5], 
		                	  mData: null, 
		                	  bSortable: false,
		                	  fnRender: function(td) {
		                		  return '<a href="' + cardBrowseUrls.find + td.aData[0] + '" title="Find in binders"' + 
		                		          'class="btn btn-mini"><i class="icon-search"></i></button>'
		                	  }
		                  }
		                  ]
	});
});