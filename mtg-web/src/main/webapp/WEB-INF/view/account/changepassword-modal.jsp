<div id="modal-changepassword" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>Change password</h3>
  </div>
  <div class="modal-body">
    <div id="changepassword-message"></div>
    <form id="form-changepassword" class="form-horizontal" action="<@spring.url '/account/changepassword' />">
      <div class="control-group">
        <label class="control-label" for="password">Current password</label>
        <div class="controls">
          <input type="password" id="password" name="password" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="newpassword">New password</label>
        <div class="controls">
          <input type="password" id="newpassword" name="newpassword" />
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="confirmpassword">Confirm new password</label>
        <div class="controls">
          <input type="password" id="confirmpassword" name="confirmpassword" />
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button id="changepassword-submit" class="btn btn-primary">Update password</button>
    <button class="btn" data-dismiss="modal">Cancel</button>
  </div>
</div>

<script>
$(function(){
	
	var $btnChangePassword = $('#btn-change-password'),
	    $modal = $('#modal-changepassword'),
	    $form = $('#form-changepassword'),
	    $btnSubmit = $('#changepassword-submit'),
	    $message = $('#changepassword-message');
	
	$btnChangePassword.click(function(){
		$modal.modal('show');
		$('.msg-changepassword-success').remove();
	});

	$form.submit(function(){
		
		$.post($form.attr('action'), $form.serialize(), function(response) {
			switch(response.status) {
			case '200':
				$form.find('input').val('');
				$modal.modal('hide');
				$message.html('');
				$('<small class="text-success ml5 msg-changepassword-success">').text('Success').insertAfter($btnChangePassword)
				break;
			case '500':
				$message.html('<div class="alert alert-error">' + response.message);
				break;
			default:
				footer.error('Could not change password!');
			}
		});
		
		return false;
	});
	
	$btnSubmit.click(function(){
		$form.submit();
	});
});
</script>