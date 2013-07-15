<#import "/spring.ftl" as spring />

<#assign action><@spring.url '/account/deck/new' /></#assign>
<form id="form-newdeck" class="form-horizontal" action="${action }" method="post">
  <fieldset>
    <legend>Create a deck</legend>
    <div class="newdeck-error">
    </div>
    <div class="control-group">
      <label class="control-label" for="name">Name</label>
      <div class="controls">
        <input type="text" name="name" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="description">Description</label>
      <div class="controls">
        <textarea name="description"></textarea>
      </div>
    </div>
  </fieldset>
  <div class="control-group">
    <div class="controls">
      <button class="btn btn-primary">Create deck</button>
      <a href="<@spring.url '/account/dashboard' />" class="btn">Cancel</a>
    </div>
  </div>    
</form>

<script>
var newdeckUrls = {
    editdeck : '<@spring.url "/account/deck/edit/" />'
}

$(function(){
  var $form = $('#form-newdeck');

  $form.submit(function(){
    
    $.post($form.attr('action'), $form.serialize(), function(response){
      switch(response.status) {
      case '200':
        window.location.href = newdeckUrls.editdeck + response.message;
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