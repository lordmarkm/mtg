<div id="modal-parsecards" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>Parse cards</h3>
  </div>
  <div class="modal-body">
    <div id="modal-parsecards-message">
      <div class="alert alert-warn"><i class="icon-exclamation-sign"></i> This could take a very long time.</div>
    </div>
    <div class="clearfix mb10"></div>
    <form action="<@spring.url '/admin/parsecards' />" id="form-parsecards" class="form-horizontal">
      <div class="control-group">
        <label class="control-label" for="url">URL</label>
        <div class="controls">
          <input type="text" name="url" />
          <span class="help-block">Must be of the form http://sales.starcitygames.com/category.php?cat=5061&start=0</span>
        </div>
      </div>
      <input type="hidden" name="expcode" value="" />
    </form>
  </div>
  <div class="modal-footer">
    <button id="form-parsecards-submit" class="btn btn-primary">Begin</button>
    <button class="btn" data-dismiss="modal">Close</button>
  </div>
</div>

<script>
$(function(){
  var
   $modal = $('#modal-parsecards'),
   $form = $('#form-parsecards'),
   $msg = $('#modal-parsecards-message'),
   $btnsubmit = $('#form-parsecards-submit');
  
  function resetmodal() {
	  $form.find('input[name="expcode"]').val('');
	  $btnsubmit.removeAttr('disabled');
  }
  
  $(document).on({
	  click: function(){
		  var $a = $(this);
		  var expcode = $(this).attr('expcode');
		  var expname = $(this).attr('expname');
		  
		  resetmodal();
		  $form.find('input[name="expcode"]').val(expcode);
		  $modal
		    .find('h3').text('Parse cards for ' + expname).end()
		    .modal('show');
		  return false;
	  }
  }, '.a-parse-cards');
  
  $form.submit(function(){
	  //if a response comes back in less than 1 second, it's probably an error
	  var showprocessing = setTimeout(function(){
		  $modal.modal('hide');
		  
		  var $a = $('a[expcode="' + $('input[name="expcode"]').val() + '"]');
		  $a.closest('.dropdown').hide();
		  $('<div class="progress progress-striped active" title="Parsing...">').append($('<div class="bar">').css('width','100%')).appendTo($a.closest('td'));
	  }, 1000);
	  
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
      $btnsubmit.removeAttr('disabled');
    	clearTimeout(showprocessing);
    }).error(function(xhr,status){
        $msg.html('<div class="alert alert-error">There was an error with your request. Please check the URL format and try again.');
    });
    return false;
  });
  
  $btnsubmit.click(function(){
	  $(this).attr('disabled', 'disabled');
	  $form.submit();
  })
});
</script>