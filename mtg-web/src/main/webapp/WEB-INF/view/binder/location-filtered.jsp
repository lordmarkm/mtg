<#import "/spring.ftl" as spring />
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#import "../templates/tools.ftl" as tools />

<h3>
  <#if location??>
    <#if location.parent??>
      <#if location.parent.parent??>
      <small>${location.parent.parent.name } ></small>
      </#if>
    <small>${location.parent.name } ></small>
    </#if>
    ${location.name?html }
    <@sec.authorize access='hasRole("ROLE_ADMIN")'>
    <#if type == 'city' || type=='meetup'>
    <button class="btn btn-danger btn-ban" type="${type }" location-id="${location.id?c }"><i class="fam-world-delete"></i> Delete this ${type }</button>
    </#if>
    </@sec.authorize>
  <#else>
  All the binders
  </#if>
</h3>

<#if binders?has_content>
<table id="filtered-binders-table">
  <thead>
    <tr>
      <th>Binder</th>
      <th></th>
      <th>Cards</th>
      <th>Last modified</th>
      <th>Owner</th>
    </tr>
  </thead>
  <tbody>
    <#list binders as binder>
    <tr>
      <td><a href="<@spring.url '/u/${binder.owner.name }/${binder.urlFragment }' />">${binder.name }</a></td>
      <td><@tools.nl2br string=binder.description /></td>
      <td>${binder.cardCount() }</td>
      <td><div class="filtered-binders-moment">${binder.lastModified }</div></td>
      <td><div class="clip80" title="${binder.owner.name }"><@tools.flag country=binder.owner.country! /> ${binder.owner.name }</div></td>
    </tr>
    </#list>
  </tbody>
</table>



<#else>
<div class="alert alert-info">No binders found!</div>
</#if>

<script>
$(function(){
	urls = {
			ban : '<@spring.url "/admin/ban/" />'
	}
	
	$('#filtered-binders-table').dataTable();
	$('.filtered-binders-moment').each(function(){
		var $td = $(this);
		var time = moment($td.text()).fromNow();
		$td.text(time).attr('title', time);
	});
	
	$('.btn-ban').click(function(){
		var $btn = $(this);
		var type = $btn.attr('type');
		var id = $btn.attr('location-id');
		
		$.post(urls.ban + type + '/' + id, function(response) {
			switch(response.status) {
			case '200':
			case '500':
				bootbox.alert(response.message);
			default:
				footer.error(response.message);
			}
		});
	});
});
</script>