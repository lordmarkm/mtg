<#import "/spring.ftl" as spring />

<div class="container">
  <div class="span8">
    <#assign action><@spring.url '/account/newbinder' /></#assign>
    <form id="form-newbinder" class="form-horizontal" action="${action }" method="post">
      <@spring.bind "form" />
      <fieldset>
        <legend>Create a binder</legend>
        <#if error??>
        <div class="alert alert-error">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          ${error }
        </div>
        </#if>
        <div class="control-group">
          <label class="control-label" for="name">Name</label>
          <div class="controls">
            <@spring.formInput 'form.name' />
            <@spring.showErrors '<br>', 'error' />
          </div>
        </div>
        <div class="control-group">
          <label class="control-label" for="description">Description</label>
          <div class="controls">
            <@spring.formTextarea 'form.description' />
            <@spring.showErrors '<br>', 'error' />
          </div>
        </div>
      </fieldset>
      <div class="control-group">
        <div class="controls">
          <button class="btn btn-primary">Create binder</button>
          <a href="<@spring.url '/account/dashboard' />" class="btn">Cancel</a>
        </div>
      </div>    
    </form>
  </div>
</div>

<script>
var newbinderUrls = {
		editbinder : '<@spring.url "/account/editbinder/" />'
}

$(function(){
	var $form = $('#form-newbinder');

	$form.submit(function(){
		
		$.post($form.attr('action'), $form.serialize(), function(response){
			switch(response.status) {
			case '200':
				window.location.href = newbinderUrls.editbinder + response.message;
				break;
			case '500':
				footer.error(response.message);
				break;
			default:
				footer.debug('Error submitting form :(');
			}
		});
		
		return false;
	});
});
</script>