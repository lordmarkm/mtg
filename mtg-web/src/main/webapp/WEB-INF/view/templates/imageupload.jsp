<div id="imageupload-modal" class="modal hide fade">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal"
      aria-hidden="true">&times;</button>
    <h3 id="imageupload-title">Upload image</h3>
  </div>
  <div class="modal-body">
    <div id="imageupload-dropbox" style="margin: auto;">
      <div id="imageupload-dropbox-loading" class="progress progress-striped active">
        <div class="bar" style="width: 100%;"></div>
      </div>
      <div id="imageupload-dropbox-instructions">
        <i class="icon-picture"></i>
        <p>Drag image here or click to browse
      </div>
    </div>
    <div id="imageupload-message">&nbsp;</div>
    <input id="file-imageupload" style="visibility: collapse; width: 0px;" type="file" />
  </div>
  <div class="modal-footer">
    <a id="imageupload-close" href="#" class="btn" data-dismiss="modal">Done</a>
  </div>
</div>

<style>
#imageupload-dropbox {
  cursor: pointer;
  border: 2px dashed grey;
  width: 100px;
  height: 100px;
  text-align: center;
  padding-top: 5px;
}
#imageupload-dropbox.hungry {
  border: 2px dashed green;
}
#imageupload-dropbox-loading {
  width: 80%;
  margin: 35px auto;
}
</style>

<script>
var urls = {
	image : '<@spring.url "/image/" />'
}

$(function(){
	  var attr = {
			uploadUrl : 'upload-url',
			dropboxLoading : 'loading'
	  }
	
	  var
	   $modal = $('#imageupload-modal'),
	   $modalTitle = $('#imageupload-title'),
	   $dropbox = $('#imageupload-dropbox'),
	   $dropboxLoading = $('#imageupload-dropbox-loading'),
	   $dropboxInstructions = $('#imageupload-dropbox-instructions'),
	   $message = $('#imageupload-message'),
	   $file = $('#file-imageupload'),
	   $close = $('#imageupload-close');
	
	  function resetModal() {
		  $modal.callback = undefined;
		  $modalTitle.text('Upload image');
      $message.html('&nbsp;');
      $dropbox.removeAttr(attr.uploadUrl).removeAttr(attr.dropboxLoading);
      $dropboxLoading.hide();
      $dropboxInstructions.show();
	  }
	  
	  $(document).on({
		  click: function() {
			  var $container = $(this);
			  var url = $container.attr(attr.uploadUrl);
			  var title = $container.attr('upload-title') ? $container.attr('upload-title') : 'Upload image';
			  
			  if(!url) {
				  alert('This replacable image has no upload url!');
				  return;
			  }
			  
			  resetModal();
			  $modalTitle.text(title);
			  $dropbox.attr(attr.uploadUrl, url);
			  $modal.modal('show');
			  
			  $modal.callback = function(response) {
				  $container.find('img').attr('src', urls.image + response.image.id + '?time=' + new Date().getTime());
			  }
			}
	  }, '.replacable-image-container');
		
	  $file.change(function(){
		  setTimeout(function(){
			  upload($file[0].files[0], $dropbox.attr(attr.uploadUrl));
		  }, 100);
	  });
	  
		$(document).on({
			click : function(){
				if($(this).attr(attr.dropboxLoading)) {
					return;
				}
			  $file.click();
		  },
		  dragover : function(e) {
		    e.preventDefault();
	      if($(this).attr(attr.dropboxLoading)) {
	        return;
	      }
		    $(this).addClass('hungry');
		  },
		  dragleave : function(e) {
		    e.preventDefault();
		    $(this).removeClass('hungry');
		  },
		  drop : function(e) {
		    e.preventDefault();
	      if($(this).attr(attr.dropboxLoading)) {
	        return;
	      }
		    $(this).removeClass('hungry');
		    upload(e.originalEvent.dataTransfer.files[0], $(this).attr(attr.uploadUrl))
		  }
		}, '#imageupload-dropbox');
		  
		function upload(file, url, callback) {
		  if (!file || !file.type.match(/image.*/)) {
        $message.html('<span class="text-error">Error uploading image. Might be an invalid format.');
			  return;
		  }
		  
		  $dropbox.attr(attr.dropboxLoading, 'loading');
		  $dropboxInstructions.hide();
		  $dropboxLoading.show();
		    
		  var form = new FormData();
		  form.append('data', file);
		   
		  $.ajax({
		    url: url,
		    data: form,
		    processData: false,
		    contentType: false,
		    type: 'POST',
		    success: function(response){
		      $modal.modal('hide');
		      if(typeof $modal.callback === 'function') {
		    	  $modal.callback(response);
		      }
		    },
		    error: function(xhr, status) {
		    	$message.html('<span class="text-error">Error uploading image. Might be too larg: max file size: 20Kb');
		    },
		    complete: function() {
		    	$dropboxLoading.hide();
		    	$dropboxInstructions.show();
		      $dropbox.removeAttr(attr.dropboxLoading);
		    }
		  });
		}
});
</script>