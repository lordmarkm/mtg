<div id="deletebinder-modal" class="modal fade hide">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h3>Confirm delete binder</h3>
  </div>
  <div class="modal-body">
    Are you sure you want to delete <span id="deletebinder-name"></span>?
  </div>
  <div class="modal-footer">
    <button id="deletebinder-confirm" class="btn btn-danger">Yes</button>
    <button class="btn" data-dismiss="modal">Cancel</button>
  </div>
</div>

<script>
$(function(){
  var
   $modal = $('#deletebinder-modal'),
   $spanName = $('#deletebinder-name'),
   $btnConfirm = $('#deletebinder-confirm'),
   $btnAddbinder = $('#btn-addbinder');
  
  //delete binder
  $btnConfirm.click(function(){
  var id = $(this).attr('binder-id');
  $.post(dashboardUrls.deleteBinder + id, function(response) {
    switch(response.status) {
    case '200':
    	console.debug(id);
      var tr = $('tr[binder-id="' + id + '"]').fadeOut();
      console.debug(tr.length);
      $btnAddbinder.show();
      $modal.modal('hide');
      break;
    default:
      footer.error('Error deleting ' + name);
    }
    }).error(function(){
      footer.error('Error deleting ' + name);
    });
  });
  
  $('.delete-binder').on({
    click: function(){
      var $btsssn = $(this);
      var name = $btsssn.attr('binder-name');
      var id = $btsssn.attr('binder-id');
      $btnConfirm.attr('binder-id', id);
      $spanName.html(name);
      $modal.modal('show');
    }
  });
});
</script>