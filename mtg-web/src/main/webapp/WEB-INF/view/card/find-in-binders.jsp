<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">cards</span>

<#if card?? && bundles?has_content>

<h3>
  <a href="<@spring.url '/cards/${card.id?c }' />">${card.name }</a>
  <br />
  <small>In Binders</small>
</h3>

<table class="table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Expansion</th>
      <th>Binder</th>
      <th></th>
      <th>Note</th>
    </tr>
  </thead>
  <tbody>
    <#list bundles as bundle>
    <tr>
      <td><a href="<@spring.url '/cards/${bundle.card.id?c }' />" target="_blank">${bundle.card.name }</a></td>
      <td>${bundle.card.expansion.name }</td>
      <td>
        <@tools.flag country=bundle.page.binder.owner.country! />
        ${bundle.page.binder.owner.name } - 
        <a href="<@spring.url '/u/${bundle.page.binder.owner.name}/${bundle.page.binder.urlFragment}' />" target="_blank">${bundle.page.binder.name }</a>
      </td>
      <td>${bundle.count }</td>
      <td>${bundle.note! }</td>
    </tr>
    </#list>
  </tbody>
</table>

<#elseif !bundles?has_content>
<div class="alert alert-info"><strong>${card.name }</strong> not found in any binders</div>
<#else>
<div class="alert alert-info">Card not found</div>
</#if>