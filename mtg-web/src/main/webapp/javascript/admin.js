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
				                      //hide the id column
				                      {'bVisible':false, 'aTargets':[0]},
				                      
				                      //link to card page
				                      {
				                    	  'fnRender': function(td) {
				                    		  return '<a href="' + urls.card + td.aData[0] + '">' + td.aData[1] + '</a>';
				                    	  },
				                    	  'aTargets' : [1]
				                      }
				                      ]
			});
		} else {
			$dt.fnReloadAjax(ajaxSource());
		}
	});
	
	function ajaxSource() {
		return urls.cardsDt + $cardselect.val() + '?ajax';
	}
});