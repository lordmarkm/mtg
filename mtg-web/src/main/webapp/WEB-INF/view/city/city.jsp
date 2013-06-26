<#import "/spring.ftl" as spring />
<#import "../templates/tools.ftl" as tools />

<span id="active-navbar-class" class="hide">communities</span>

<#if city.banned>
<div class="alert alert-error">This city has been banned</div>
<#elseif (city.players?size == 0)>
<div class="alert alert-info">There is nobody in this city</div>
<#else>
<h3>People in ${city.name }</h3>
<table class="table table-striped">
  <thead>
    <tr>
      <th>Name</th>
      <th>Binders</th>
    </tr>
  </thead>
  <tbody>
    <#list city.players as player>
    <tr>
      <td><a href="/u/${player.name }">${player.name }</a></td>
      <td>
        <ul class="unstyled">
        <#list player.binders as binder>
          <li><a href="<@spring.url '/u/${player.name }/${binder.urlFragment }' />">${binder.name }</a> - ${binder.cardCount() } cards
        </#list>
        </ul>
      </td>
    </tr>
    </#list>
  </tbody>
</table>

</#if>