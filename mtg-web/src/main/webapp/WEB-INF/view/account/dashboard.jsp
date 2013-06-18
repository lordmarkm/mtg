<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />

<h3>Account Details</h3>
<dl>
  <dt>Username</dt>
  <dd><@sec.authentication property="principal.username" /></dd>
  <dt>Authorities</dt>
  <dd><@sec.authentication property="principal.authorities" /></dd>
</dl>

<h3>Magic Profile</h3>
<div class="row-fluid">
  <div class="span4">
    <ul class="thumbnails">
      <li>
        <div class="thumbnail">
          <div class="replacable-image-container" upload-url="<@spring.url '/account/upload/profilepic' />"
           upload-title="Change profile picture">
            <#if account.player.image??>
            <img alt="300x200" src="<@spring.url '/image/${account.player.image.id }' />" />
            <#else>
            <img alt="300x200" src="<@spring.url '/images/no-img.jpg' />" />
            </#if>
          </div>
          <div class="caption">
            <span class="muted">Click to change</span>
          </div>
        </div>
      </li>
    </ul>
  </div>
  <div class="span6">
    <dl>
      <dt>Name</dt>
      <dd>${account.player.name }</dd>
    </dl>
  </div>
</div>

<h3>Binders</h3>
<a class="btn btn-primary pull-right" href="/account/newbinder"><i class="icon-plus icon-white"></i> Create a binder</a>
<div class="clearfix mb10"></div>

<#if account.player.binders?has_content>
<table class="table">
<#list account.player.binders as binder>
  <tr>
    <td>
      <strong><a href="<@spring.url '/u/${account.player.name }/${binder.urlFragment }' />">${binder.name }</a></strong>
    </td>
    <td>
      <a href="<@spring.url '/account/editbinder/${binder.urlFragment}' />" class="btn btn-small"><i class="icon-edit"></i> Edit</a>
      <a href="javascript:;" class="delete-binder btn btn-small" binder-name="${binder.name }" binder-id="${binder.urlFragment }"><i class="icon-remove"></i> Delete</a>
    </td>
  </tr>
</#list>
</table>
<#else>
<div class="alert alert-info">You have no binders right now</div>
</#if>


<script>
var dashboardUrls = {
		deleteBinder : '<@spring.url "/account/deletebinder/" />'
}
$(function(){
	$(document).on({
		click: function(){
			var $btn = $(this);
			var name = $btn.attr('binder-name');
			var id = $btn.attr('binder-id');
			
			if(confirm('Are you sure you want to delete ' + name + '?')) {
				$.post(dashboardUrls.deleteBinder + id, function(response) {
					switch(response.status) {
					case '200':
						$btn.closest('tr').fadeOut();
						break;
					default:
						footer.error('Error deleting ' + name);
					}
				}).error(function(){
					footer.error('Error deleting ' + name);
				});
			}
		}
	}, '.delete-binder')
});
</script>

<div class="mb50"></div>