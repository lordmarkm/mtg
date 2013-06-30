<#import "/spring.ftl" as spring />

<form id="form-post" method="post" action="#">
  <fieldset>
    <legend>New frontpage post</legend>
    <#if error??>
    <div class="alert alert-error">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      ${error }
    </div>
    </#if>
    <div class="control-group">
      <label class="control-label" for="title">Title</label>
      <div class="controls">
        <input id="title" type="text" name="title" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="text">Text</label>
      <div class="controls">
        <textarea id="text" name="text" style="width: 100%"></textarea>
      </div>
    </div>
  </fieldset>
</form>

<button id="btn-frontpage-post-submit" class="btn">Create post</button>
<a id="btn-frontpage" class="btn" href="<@spring.url '/support/frontpage' />">Back to frontpage</a>

<script>
var frontpageNewpostUrls = {
		newpost : '<@spring.url "/post/frontpage" />'
}

$(function(){
	var 
	 $btnSubmit = $('#btn-frontpage-post-submit'),
	 $btnBack = $('#btn-frontpage'),
	 $form = $('#form-post');
	
	$btnSubmit.click(function(){
		
		$.post(frontpageNewpostUrls.newpost, $form.serialize(), function(response) {
			switch(response.status) {
			case '200':
				$btnBack.click();
				break;
			case '500':
				footer.error(response.message);
				break;
			default:
				footer.error('Error creating post');
			}
		});
		
	});
});
</script>