<div id="modal-editcontact" style="width: 400px;" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>Edit contact information</h3>
  </div>
  <div class="modal-body">
    <form id="form-editcontact" action="<@spring.url '/account/editcontact' />">
      <textarea id="textarea-contact" style="width: 80%;">${account.player.contact! }</textarea>
      <div class="help-block">
	     Allows a, b, blockquote, br, cite, code, dd, dl, dt, em, i, li, ol, p, pre, q, small, strike, strong, sub, sup, u, ul,
	     and appropriate attributes. Does not allow images.
	    </div>
    </form>
  </div>
  <div class="modal-footer">
    <button id="editcontact-submit" class="btn btn-primary">Save information</button>
    <button class="btn" data-dismiss="modal">Cancel</button>
  </div>
</div>

<script>
$(function(){
  var
   $btnEdit = $('#edit-contact'),
   $modal = $('#modal-editcontact'),
   $form = $('#form-editcontact'),
   $contact = $('#player-contact'),
   $txtContact = $('#textarea-contact'),
   $btnsubmit = $('#editcontact-submit');
  
  $btnEdit.click(function(){
    $modal.modal('show');
  });
  
  $btnsubmit.click(function(){
    var contact = $txtContact.val();
    console.debug('submitting ' + contact);
    $.post($form.attr('action'), {contact:contact}, function(response){
      switch(response.status) {
      case '200':
        $contact.html(mtgTools.nl2br(contact));
        $modal.modal('hide');
        break;
      default:
        footer.error('Error setting contact info');
      }
    });
  });
});
</script>