<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />
<span id="active-navbar-class" class="hide">communities</span>

<h3>${location.name }</h3>

<#switch type>
  <#case 'country'>
  <@tools.countrynav country=location active=1/>  
    <#break>
  <#case 'city'>
  <@tools.citynav city=location active=1/>
    <#break>
  <#case 'meetup'>
  <@tools.meetupnav meetup=location active=1/>
    <#break>
</#switch>

<form id="form-post" method="post" action="#">
  <#if type != 'frontpage'>
  <input type="hidden" name="parentId" value="${location.id }" />
  </#if>
  <fieldset>
    <legend>New post</legend>
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
      <label class="control-label" for="link">Link (optional)</label>
      <div class="controls">
        <input id="link" type="text" name="link" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="text">Text</label>
      <div class="controls">
        <textarea id="text" name="text" style="width: 60%"></textarea>
      </div>
    </div>
  </fieldset>
</form>
<button id="btn-post-submit" class="btn btn-primary">Create post</button>

<#switch type>
  <#case 'frontpage'>
  <a id="btn-back" class="btn" href="<@spring.url '/support/frontpage' />">Back to frontpage</a>
    <#break>
  <#case 'country'>
  <a id="btn-back" class="btn" href="<@spring.url '/cy/${location.urlFragment }' />">Back to ${location.name }</a>
    <#break>
  <#case 'city'>
  <a id="btn-back" class="btn" href="<@spring.url '/ct/${location.urlFragment }' />">Back to ${location.name }</a>
    <#break>
  <#case 'meetup'>
  <a id="btn-back" class="btn" href="<@spring.url '/m/${location.urlFragment }' />">Back to ${location.name }</a>  
    <#break>
</#switch>

<script>
var newpostUrls = {
    newpost : '<@spring.url "/post/${type}" />'
}

$(function(){
  var 
   $btnSubmit = $('#btn-post-submit'),
   $btnBack = $('#btn-back'),
   $form = $('#form-post');
  
  $btnSubmit.click(function(){
    
    $.post(newpostUrls.newpost, $form.serialize(), function(response) {
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