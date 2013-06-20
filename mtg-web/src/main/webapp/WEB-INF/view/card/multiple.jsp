<#import "/spring.ftl" as spring />

<#if cards?has_content>

<h3>${cards[0].name } <small>found in multiple sets</small></h3>

<table class="table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Expansion</th>
      <th>Cost</th>
      <th>Rarity</th>
    </tr>
  </thead>
  <tbody>
    <#list cards as card>
    <tr>
      <td><a href="<@spring.url '/cards/${card.id }' />">${card.name }</a></td>
      <td>${card.expansion.name }</td>
      <td>${card.cost }</td>
      <td>${card.rarity }</td>
    </tr>
    </#list>
  </tbody>
</table>

<div class="pull-left">
  <a class="btn" href="<@spring.url '/cards/find/${cards[0].id }' />">Find in binders</a>
</div>

<#else>
<div class="alert alert-info">Cards not found</div>
</#if>