<div id="modal-newexpansion" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>Add an Expansion</h3>
  </div>
  <div class="modal-body">
    <div id="modal-newexpansion-message">&nbsp;</div>
    <div class="clearfix mb10"></div>
    <form action="<@spring.url '/admin/newexpansion' />" id="form-newexpansion" class="form-horizontal">
      <div class="control-group">
        <label class="control-label" for="name">Name</label>
        <div class="controls">
          <input type="text" name="name" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="name">Code</label>
        <div class="controls">
          <input type="text" name="code" />
          <span class="help-block">TSP, RTR, M14, etc. </span>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="name">Description</label>
        <div class="controls">
          <textarea name="description"></textarea>
          <span class="help-block">Prefer something like '3rd expansion in the Ravnica Block'</span>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button id="form-newexpansion-submit" class="btn btn-primary">Add Expansion</button>
    <button class="btn" data-dismiss="modal">Cancel</button>
  </div>
</div>

<script>
$(function(){
	var
	 $modal = $('#modal-newexpansion'),
	 $form = $('#form-newexpansion'),
	 $msg = $('#modal-newexpansion-message'),
	 $btnsubmit = $('#form-newexpansion-submit');
	
	$form.submit(function(){
		$.post($form.attr('action'), $form.serialize(), function(response){
			switch(response.status) {
			case '200':
				$modal.modal('hide');
				break;
			case '500':
				$msg.html('<span class="text-error">' + response.message);
				break;
			default:
		    $msg.html('<span class="text-error">Something went wrong creating the expansion.');
			}
		}).complete(function(){
		  $btnsubmit.button('enable');
		});
		return false;
	});
	
	$btnsubmit.click(function(){
		$(this).button('disable');
		$form.submit();
	})
});
</script>