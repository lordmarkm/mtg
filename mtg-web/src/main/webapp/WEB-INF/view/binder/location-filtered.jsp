<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<#if binders?has_content>

<h3>

<#if location??>
<#if location.parent??>
<#if location.parent.parent??>
<small>${location.parent.parent.name } ></small>
</#if>
<small>${location.parent.name } ></small>
</#if>
${location.name }
<#else>
All the binders
</#if>

</h3>

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
      <td>${binder.name }</td>
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
	$('#filtered-binders-table').dataTable();
	$('.filtered-binders-moment').each(function(){
		var $td = $(this);
		var time = moment($td.text()).fromNow();
		$td.text(time).attr('title', time);
	});
});
</script>