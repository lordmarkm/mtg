
//displays a table of cards that changes depending on the selected expansion

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
				                    		  return '<button card-id="' + td.aData[0] + '" class="btn btn-mini card-add" title="Add card"><i class="icon-plus"></i></button>';
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
		return urls.cardsDt + $cardselect.val() + '?ajax';
	}
});